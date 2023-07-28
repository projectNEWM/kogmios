package io.newm.kogmios

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.websocket.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.*
import io.ktor.util.reflect.*
import io.ktor.utils.io.charsets.*
import io.ktor.websocket.*
import io.newm.kogmios.exception.KogmiosException
import io.newm.kogmios.protocols.messages.*
import io.newm.kogmios.protocols.model.*
import io.newm.kogmios.protocols.model.fault.InternalErrorFault
import io.newm.kogmios.protocols.model.fault.StringFaultData
import io.newm.kogmios.serializers.BigFractionSerializer
import io.newm.kogmios.serializers.BigIntegerSerializer
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ClosedReceiveChannelException
import kotlinx.coroutines.channels.ClosedSendChannelException
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import org.apache.commons.numbers.fraction.BigFraction
import org.slf4j.LoggerFactory
import java.io.IOException
import java.math.BigInteger
import java.util.*
import java.util.concurrent.ConcurrentHashMap
import kotlin.collections.set
import kotlin.coroutines.CoroutineContext

internal class ClientImpl(
    private val websocketHost: String,
    private val websocketPort: Int,
    private val secure: Boolean = false,
    private val ogmiosCompact: Boolean = false,
    loggerName: String? = null,
) : CoroutineScope, StateQueryClient, TxMonitorClient, ChainSyncClient, TxSubmitClient {
    private val log by lazy {
        if (loggerName == null) {
            LoggerFactory.getLogger(ClientImpl::class.java)
        } else {
            LoggerFactory.getLogger(loggerName)
        }
    }

    override var isConnected: Boolean = false
        private set
    private var isClosing = false
    private var fatalException: Throwable? = null
    private val sendClose = CompletableDeferred<Unit>()
    private val receiveClose = CompletableDeferred<Unit>()

    private val sendQueue = Channel<JsonRpcRequest>(Channel.BUFFERED)
    private val requestResponseMap = ConcurrentHashMap<String, CompletableDeferred<JsonRpcSuccessResponse>>()

    private lateinit var session: DefaultClientWebSocketSession
    private val httpClient by lazy {
        HttpClient(CIO) {
            engine {
                // no timeout for websockets since they will stay open
                requestTimeout = 0L
            }
            install(WebSockets) {
                contentConverter =
                    object : WebsocketContentConverter {
                        override fun isApplicable(frame: Frame): Boolean = frame.frameType == FrameType.TEXT

                        override suspend fun serialize(
                            charset: Charset,
                            typeInfo: TypeInfo,
                            value: Any
                        ): Frame {
                            log.trace("serialize() - charset: {}, typeInfo: {}, value: {}", charset, typeInfo, value)
                            return Frame.Text(
                                when (value) {
                                    is JsonRpcRequest ->
                                        json.encodeToString(value).also {
                                            // temporary while we need to figure out how all the requests look
                                            log.debug("sending: {}", it)
                                        }

                                    else -> throw IllegalArgumentException("Unable to serialize ${value::class.java.canonicalName}")
                                },
                            )
                        }

                        override suspend fun deserialize(
                            charset: Charset,
                            typeInfo: TypeInfo,
                            content: Frame
                        ): Any {
                            log.trace("deserialize() - charset: {}, typeInfo: {}, content: {}", charset, typeInfo, content)
                            val jsonString = (content as Frame.Text).readText()
                            log.debug("received: {}", jsonString)
                            return try {
                                json.decodeFromString<JsonRpcResponse>(jsonString)
                            } catch (e: Throwable) {
                                // This should never happen unless WE have made an error in our parsers somewhere.
                                val idRegex = """"id":"([^:{},]*: [a-f0-9]{8}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{12})"""".toRegex()
                                val id = idRegex.find(jsonString)?.let { it.groupValues[1] } ?: "-1"
                                JsonRpcErrorResponse(
                                    error =
                                        InternalErrorFault(
                                            code = -1,
                                            message = "Error parsing JsonRpcResponse!",
                                            data = StringFaultData(jsonString),
                                        ),
                                    id = id,
                                    cause = e,
                                )
                            }
                        }
                    }
            }
        }
    }

    override suspend fun connect(): Boolean {
        return connectInternal().await()
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun connectInternal(): CompletableDeferred<Boolean> {
        val result = CompletableDeferred<Boolean>()

        launch {
            try {
                httpClient.webSocket(
                    method = HttpMethod.Get,
                    host = websocketHost,
                    port = websocketPort,
                    request = {
                        if (secure) {
                            url("wss", websocketHost, websocketPort)
                        }
                        if (ogmiosCompact) {
                            headers {
                                append("Sec-WebSocket-Protocol", "ogmios.v1:compact")
                            }
                        }
                    },
                ) {
                    delay(1000) // wait a bit in case server disconnects us immediately.
                    if (!coroutineContext.job.isActive) {
                        result.complete(false)
                    } else {
                        session = this
                        isConnected = true
                        val startupMutex = Mutex()
                        var receivingReady = false
                        var sendingReady = false
                        awaitAll(
                            // Receive Loop
                            async {
                                while (!session.incoming.isClosedForReceive) {
                                    try {
                                        startupMutex.withLock {
                                            if (!receivingReady) {
                                                receivingReady = true
                                                if (sendingReady) {
                                                    result.complete(true)
                                                }
                                            }
                                        }
                                        when (val response = session.receiveDeserialized<JsonRpcResponse>()) {
                                            is JsonRpcErrorResponse -> {
                                                log.error("response: {}", response)
                                                if (!requestResponseMap.containsKey(response.id)) {
                                                    log.error("No handler found for: {}", response.id)
                                                    log.error("requestResponseMap: {}", requestResponseMap)
                                                }
                                                requestResponseMap.remove(response.id)!!.completeExceptionally(
                                                    KogmiosException(
                                                        message = response.error.message,
                                                        cause = response.cause ?: IOException(),
                                                        jsonRpcErrorResponse = response,
                                                    ),
                                                )
                                            }

                                            is JsonRpcSuccessResponse -> {
                                                log.debug("response: {}", response)
                                                requestResponseMap.remove(response.id)
                                                    ?.complete(response)
                                                    ?: log.warn("No handler found for: {}", response.id)
                                            }
                                        }
                                    } catch (e: ClosedReceiveChannelException) {
                                        if (!isClosing) {
                                            log.warn("websocketClient.incoming was closed unexpectedly.")
                                            fatalException = e
                                            isConnected = false
                                            break
                                        }
                                    }
                                }
                                if (isClosing) {
                                    log.debug("websocketClient.incoming was closed.")
                                }
                                receiveClose.complete(Unit)
                                fatalException?.let {
                                    shutdownExceptionally()
                                    throw it
                                }
                            },
                            // Send Loop
                            async {
                                while (!session.outgoing.isClosedForSend) {
                                    try {
                                        startupMutex.withLock {
                                            if (!sendingReady) {
                                                sendingReady = true
                                                if (receivingReady) {
                                                    result.complete(true)
                                                }
                                            }
                                        }
                                        sendQueue.consumeEach { request ->
                                            log.debug("request: {}", request)
                                            requestResponseMap[request.id] = request.completableDeferred
                                            session.sendSerialized(request)
                                        }
                                    } catch (e: ClosedSendChannelException) {
                                        if (!isClosing) {
                                            log.warn("websocketClient.outgoing was closed unexpectedly.")
                                            fatalException = e
                                            isConnected = false
                                            break
                                        }
                                    }
                                }
                                if (isClosing) {
                                    log.debug("websocketClient.outgoing was closed.")
                                }
                                sendClose.complete(Unit)
                                fatalException?.let {
                                    shutdownExceptionally()
                                    throw it
                                }
                            },
                        )
                    }
                    log.debug("Websocket completed normally.")
                    if (result.isActive) {
                        result.complete(false)
                    }
                }
            } catch (e: Throwable) {
                log.error("Connection Error!", e)
                e.printStackTrace()
                if (result.isActive) {
                    result.complete(false)
                }
            }
        }
        return result
    }

    override suspend fun acquire(
        pointOrOrigin: PointOrOrigin,
        timeoutMs: Long
    ): MsgAcquireResponse {
        assertConnected()

        val message =
            MsgAcquire(
                params = pointOrOrigin,
            )

        sendQueue.send(message)
        return coroutineScope {
            withTimeout(timeoutMs) {
                message.completableDeferred.await() as MsgAcquireResponse
            }
        }
    }

    override suspend fun release(timeoutMs: Long): MsgReleaseResponse {
        assertConnected()
        val message = MsgRelease()
        sendQueue.send(message)
        return coroutineScope {
            withTimeout(timeoutMs) {
                message.completableDeferred.await() as MsgReleaseResponse
            }
        }
    }

    override suspend fun chainTip(timeoutMs: Long): MsgQueryTipResponse {
        assertConnected()
        val message =
            MsgQuery(
                method = MsgQuery.METHOD_QUERY_NETWORK_TIP,
            )
        sendQueue.send(message)
        return coroutineScope {
            withTimeout(timeoutMs) {
                message.completableDeferred.await() as MsgQueryTipResponse
            }
        }
    }

    override suspend fun stakePools(
        pools: List<String>,
        timeoutMs: Long
    ): MsgQueryStakePoolsResponse {
        assertConnected()
        val message =
            MsgQuery(
                method = MsgQuery.METHOD_QUERY_LEDGER_STATE_STAKE_POOLS,
                params = ParamsPoolParameters(pools.map { StakePool(it) }),
            )
        sendQueue.send(message)
        return coroutineScope {
            withTimeout(timeoutMs) {
                message.completableDeferred.await() as MsgQueryStakePoolsResponse
            }
        }
    }

    override suspend fun blockHeight(timeoutMs: Long): MsgQueryBlockHeightResponse {
        assertConnected()
        val message =
            MsgQuery(
                method = MsgQuery.METHOD_QUERY_NETWORK_BLOCK_HEIGHT,
            )
        sendQueue.send(message)
        return coroutineScope {
            withTimeout(timeoutMs) {
                message.completableDeferred.await() as MsgQueryBlockHeightResponse
            }
        }
    }

    override suspend fun protocolParameters(timeoutMs: Long): MsgQueryProtocolParametersResponse {
        assertConnected()
        val message =
            MsgQuery(
                method = MsgQuery.METHOD_QUERY_LEDGER_STATE_PROTOCOL_PARAMETERS,
            )
        sendQueue.send(message)
        return coroutineScope {
            withTimeout(timeoutMs) {
                message.completableDeferred.await() as MsgQueryProtocolParametersResponse
            }
        }
    }

    override suspend fun epoch(timeoutMs: Long): MsgQueryEpochResponse {
        assertConnected()
        val message =
            MsgQuery(
                method = MsgQuery.METHOD_QUERY_LEDGER_STATE_EPOCH,
            )
        sendQueue.send(message)
        return coroutineScope {
            withTimeout(timeoutMs) {
                message.completableDeferred.await() as MsgQueryEpochResponse
            }
        }
    }

    override suspend fun rewardAccountSummaries(
        stakeAddresses: List<String>,
        timeoutMs: Long
    ): MsgQueryRewardAccountSummariesResponse {
        assertConnected()
        val message =
            MsgQuery(
                method = MsgQuery.METHOD_QUERY_LEDGER_STATE_REWARD_ACCOUNT_SUMMARIES,
                params = ParamsRewardAccountSummaries(stakeAddresses)
            )
        sendQueue.send(message)
        return coroutineScope {
            withTimeout(timeoutMs) {
                message.completableDeferred.await() as MsgQueryRewardAccountSummariesResponse
            }
        }
    }

    override suspend fun eraStart(timeoutMs: Long): MsgQueryEraStartResponse {
        assertConnected()
        val message =
            MsgQuery(
                method = MsgQuery.METHOD_QUERY_LEDGER_STATE_ERA_START,
            )
        sendQueue.send(message)
        return coroutineScope {
            withTimeout(timeoutMs) {
                message.completableDeferred.await() as MsgQueryEraStartResponse
            }
        }
    }

    override suspend fun eraSummaries(timeoutMs: Long): MsgQueryEraSummariesResponse {
        assertConnected()
        val message =
            MsgQuery(
                method = MsgQuery.METHOD_QUERY_LEDGER_STATE_ERA_SUMMARIES,
            )
        sendQueue.send(message)
        return coroutineScope {
            withTimeout(timeoutMs) {
                message.completableDeferred.await() as MsgQueryEraSummariesResponse
            }
        }
    }

    override suspend fun genesisConfig(
        era: GenesisEra,
        timeoutMs: Long
    ): MsgQueryGenesisConfigResponse {
        assertConnected()
        val message =
            MsgQuery(
                method = MsgQuery.METHOD_QUERY_NETWORK_GENESIS_CONFIGURATION,
                params = ParamsGenesisConfig(era.name.lowercase()),
            )
        sendQueue.send(message)
        return coroutineScope {
            withTimeout(timeoutMs) {
                message.completableDeferred.await() as MsgQueryGenesisConfigResponse
            }
        }
    }

    override suspend fun projectedRewards(
        params: ParamsProjectedRewards,
        timeoutMs: Long
    ): MsgQueryProjectedRewardsResponse {
        assertConnected()
        val message =
            MsgQuery(
                method = MsgQuery.METHOD_QUERY_LEDGER_STATE_PROJECTED_REWARDS,
                params = params,
            )
        sendQueue.send(message)
        return coroutineScope {
            withTimeout(timeoutMs) {
                message.completableDeferred.await() as MsgQueryProjectedRewardsResponse
            }
        }
    }

    override suspend fun proposedProtocolParameters(timeoutMs: Long): MsgQueryProposedProtocolParametersResponse {
        assertConnected()
        val message =
            MsgQuery(
                method = MsgQuery.METHOD_QUERY_LEDGER_STATE_PROPOSED_PROTOCOL_PARAMETERS,
            )
        sendQueue.send(message)
        return coroutineScope {
            withTimeout(timeoutMs) {
                message.completableDeferred.await() as MsgQueryProposedProtocolParametersResponse
            }
        }
    }

    override suspend fun liveStakeDistribution(timeoutMs: Long): MsgQueryLiveStakeDistributionResponse {
        assertConnected()
        val message =
            MsgQuery(
                method = MsgQuery.METHOD_QUERY_LEDGER_STATE_LIVE_STAKE_DISTRIBUTION,
            )
        sendQueue.send(message)
        return coroutineScope {
            withTimeout(timeoutMs) {
                message.completableDeferred.await() as MsgQueryLiveStakeDistributionResponse
            }
        }
    }

    override suspend fun networkStartTime(timeoutMs: Long): MsgQueryNetworkStartTimeResponse {
        assertConnected()
        val message =
            MsgQuery(
                method = MsgQuery.METHOD_QUERY_NETWORK_START_TIME,
            )
        sendQueue.send(message)
        return coroutineScope {
            withTimeout(timeoutMs) {
                message.completableDeferred.await() as MsgQueryNetworkStartTimeResponse
            }
        }
    }

    override suspend fun utxo(
        params: ParamsUtxo,
        timeoutMs: Long
    ): MsgQueryUtxoResponse {
        assertConnected()
        val message =
            MsgQuery(
                method = MsgQuery.METHOD_QUERY_LEDGER_STATE_UTXO,
                params = params,
            )
        sendQueue.send(message)
        return coroutineScope {
            withTimeout(timeoutMs) {
                message.completableDeferred.await() as MsgQueryUtxoResponse
            }
        }
    }

    override suspend fun acquireMempool(timeoutMs: Long): MsgAcquireMempoolResponse {
        assertConnected()
        val message = MsgAcquireMempool()
        sendQueue.send(message)
        return coroutineScope {
            withTimeout(timeoutMs) {
                message.completableDeferred.await() as MsgAcquireMempoolResponse
            }
        }
    }

    override suspend fun releaseMempool(timeoutMs: Long): MsgReleaseMempoolResponse {
        assertConnected()
        val message = MsgReleaseMempool()
        sendQueue.send(message)
        return coroutineScope {
            withTimeout(timeoutMs) {
                message.completableDeferred.await() as MsgReleaseMempoolResponse
            }
        }
    }

    override suspend fun hasTransaction(
        txId: String,
        timeoutMs: Long
    ): MsgHasTransactionResponse {
        assertConnected()
        val message = MsgHasTransaction(params = HasTransaction(id = txId))
        sendQueue.send(message)
        return coroutineScope {
            withTimeout(timeoutMs) {
                message.completableDeferred.await() as MsgHasTransactionResponse
            }
        }
    }

    override suspend fun sizeOfMempool(timeoutMs: Long): MsgSizeOfMempoolResponse {
        assertConnected()
        val message = MsgSizeOfMempool()
        sendQueue.send(message)
        return coroutineScope {
            withTimeout(timeoutMs) {
                message.completableDeferred.await() as MsgSizeOfMempoolResponse
            }
        }
    }

    override suspend fun nextTransaction(timeoutMs: Long): MsgNextTransactionResponse {
        assertConnected()
        val message = MsgNextTransaction()
        sendQueue.send(message)
        return coroutineScope {
            withTimeout(timeoutMs) {
                message.completableDeferred.await() as MsgNextTransactionResponse
            }
        }
    }

    override suspend fun findIntersect(
        points: List<PointDetailOrOrigin>,
        timeoutMs: Long
    ): MsgFindIntersectResponse {
        assertConnected()
        val message = MsgFindIntersect(params = FindIntersect(points))
        sendQueue.send(message)
        return coroutineScope {
            withTimeout(timeoutMs) {
                message.completableDeferred.await() as MsgFindIntersectResponse
            }
        }
    }

    override suspend fun nextBlock(timeoutMs: Long): MsgNextBlockResponse {
        assertConnected()
        val message = MsgNextBlock()
        sendQueue.send(message)
        return coroutineScope {
            withTimeout(timeoutMs) {
                message.completableDeferred.await() as MsgNextBlockResponse
            }
        }
    }

    override suspend fun submit(
        tx: String,
        timeoutMs: Long
    ): MsgSubmitTxResponse {
        assertConnected()
        val message =
            MsgSubmitTx(
                params = SubmitOrEvalTx(Cbor(tx)),
            )
        sendQueue.send(message)
        return coroutineScope {
            withTimeout(timeoutMs) {
                message.completableDeferred.await() as MsgSubmitTxResponse
            }
        }
    }

    override suspend fun evaluate(
        tx: String,
        timeoutMs: Long
    ): MsgEvaluateTxResponse {
        assertConnected()
        val message =
            MsgEvaluateTx(
                params = SubmitOrEvalTx(Cbor(tx)),
            )
        sendQueue.send(message)
        return coroutineScope {
            withTimeout(timeoutMs) {
                message.completableDeferred.await() as MsgEvaluateTxResponse
            }
        }
    }

    override fun close() {
        shutdown()
    }

    /**
     * Disconnect from the Ogmios server
     */
    override fun shutdown() {
        if (isConnected) {
            runBlocking {
                isClosing = true
                log.debug("Closing WebSocket...")
                session.close(CloseReason(CloseReason.Codes.NORMAL, "Ok"))
                receiveClose.await()
                sendQueue.close()
                sendClose.await()
                httpClient.close()
            }
            isConnected = false
        }
    }

    private fun shutdownExceptionally() {
        fatalException?.let {
            // blow up any pending requests to us
            requestResponseMap.keys.forEach { key ->
                requestResponseMap.remove(key)?.completeExceptionally(it)
            }
        }
    }

    @Throws(IllegalStateException::class)
    private fun assertConnected() {
        if (!isConnected) {
            fatalException?.let {
                throw it
            } ?: throw IllegalStateException("Kogmios client must be connected! Did you forget to call connect()?")
        }
    }

    private val job by lazy { SupervisorJob() }
    override val coroutineContext: CoroutineContext by lazy {
        job + Dispatchers.IO + DefaultCoroutineExceptionHandler(log)
    }

    companion object {
        internal val json =
            Json {
                // classDiscriminator = "type"
                encodeDefaults = true
                explicitNulls = true
                ignoreUnknownKeys = true
                isLenient = true
                serializersModule =
                    SerializersModule {
                        contextual(BigInteger::class, BigIntegerSerializer)
                        contextual(BigFraction::class, BigFractionSerializer)
                    }
            }
    }
}
