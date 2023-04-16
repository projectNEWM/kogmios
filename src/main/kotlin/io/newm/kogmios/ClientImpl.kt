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
import io.newm.kogmios.serializers.BigDecimalSerializer
import io.newm.kogmios.serializers.BigIntegerSerializer
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ClosedReceiveChannelException
import kotlinx.coroutines.channels.ClosedSendChannelException
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import org.slf4j.LoggerFactory
import java.io.IOException
import java.math.BigDecimal
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

    private val sendQueue = Channel<JsonWspRequest>(Channel.BUFFERED)

    private val acquireRequestResponseMap = ConcurrentHashMap<String, CompletableDeferred<MsgAcquireResponse>>()
    private val releaseRequestResponseMap = ConcurrentHashMap<String, CompletableDeferred<MsgReleaseResponse>>()
    private val queryRequestResponseMap = ConcurrentHashMap<String, CompletableDeferred<MsgQueryResponse>>()
    private val findIntersectRequestResponseMap =
        ConcurrentHashMap<String, CompletableDeferred<MsgFindIntersectResponse>>()
    private val requestNextRequestResponseMap = ConcurrentHashMap<String, CompletableDeferred<MsgRequestNextResponse>>()
    private val submitTxResponseMap = ConcurrentHashMap<String, CompletableDeferred<MsgSubmitTxResponse>>()
    private val evaluateTxResponseMap = ConcurrentHashMap<String, CompletableDeferred<MsgEvaluateTxResponse>>()
    private val awaitAcquireResponseMap =
        ConcurrentHashMap<String, CompletableDeferred<MsgAwaitAcquireMempoolResponse>>()
    private val releaseMempoolResponseMap = ConcurrentHashMap<String, CompletableDeferred<MsgReleaseMempoolResponse>>()
    private val hasTxResponseMap = ConcurrentHashMap<String, CompletableDeferred<MsgHasTxResponse>>()
    private val sizeAndCapacityResponseMap =
        ConcurrentHashMap<String, CompletableDeferred<MsgSizeAndCapacityResponse>>()
    private val nextTxResponseMap =
        ConcurrentHashMap<String, CompletableDeferred<MsgNextTxResponse>>()

    private val requestResponseMaps = listOf(
        acquireRequestResponseMap,
        releaseRequestResponseMap,
        queryRequestResponseMap,
        findIntersectRequestResponseMap,
        requestNextRequestResponseMap,
        submitTxResponseMap,
        evaluateTxResponseMap,
        awaitAcquireResponseMap,
        releaseMempoolResponseMap,
        hasTxResponseMap,
        sizeAndCapacityResponseMap,
        nextTxResponseMap,
    )

    private lateinit var session: DefaultClientWebSocketSession
    private val httpClient by lazy {
        HttpClient(CIO) {
            engine {
                // no timeout for websockets since they will stay open
                requestTimeout = 0L
            }
            install(WebSockets) {
                contentConverter = object : WebsocketContentConverter {
                    override fun isApplicable(frame: Frame): Boolean = frame.frameType == FrameType.TEXT

                    override suspend fun serialize(charset: Charset, typeInfo: TypeInfo, value: Any): Frame {
                        if (log.isTraceEnabled) {
                            log.trace("serialize() - charset: $charset, typeInfo: $typeInfo, value: $value")
                        }
                        return Frame.Text(
                            when (value) {
                                is JsonWspRequest -> json.encodeToString(value).also {
                                    // temporary while we need to figure out how all the requests look
                                    log.debug(it)
                                }

                                else -> throw IllegalArgumentException("Unable to serialize ${value::class.java.canonicalName}")
                            }
                        )
                    }

                    override suspend fun deserialize(charset: Charset, typeInfo: TypeInfo, content: Frame): Any {
                        if (log.isTraceEnabled) {
                            log.trace("deserialize() - charset: $charset, typeInfo: $typeInfo, content: $content")
                        }
                        val jsonString = (content as Frame.Text).readText()
                        // temporary while we need to figure out how all the responses look
                        log.debug(jsonString)
                        return try {
                            json.decodeFromString<JsonWspResponse>(jsonString)
                        } catch (e: Throwable) {
                            handleError(jsonString, e)
                            IgnoredJsonWspResponse()
                        }
                    }

                    @kotlin.jvm.Throws(KogmiosException::class, IOException::class)
                    private fun handleError(jsonString: String, e: Throwable) {
                        val exception = try {
                            val jsonWspFault = json.decodeFromString<JsonWspFault>(jsonString)
                            KogmiosException(jsonWspFault.fault.string, e, jsonWspFault)
                        } catch (parseException: Throwable) {
                            log.error("Error parsing Fault!", parseException)
                            IOException(jsonString, e)
                        }
                        requestResponseMaps.forEach outer@{ requestResponseMap ->
                            requestResponseMap.keys.forEach inner@{ key ->
                                if (key in jsonString) {
                                    requestResponseMap.remove(key)?.completeExceptionally(exception)
                                    return@outer
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    override suspend fun connect(): Boolean {
        return connectInternal().await()
    }

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
                    }
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
                                        val response = session.receiveDeserialized<JsonWspResponse>()
                                        if (log.isDebugEnabled) {
                                            log.debug("response: $response")
                                        }
                                        when (response) {
                                            is IgnoredJsonWspResponse -> {
                                                // A fault likely happened and is already handled. We ignore the response value.
                                            }

                                            is MsgAcquireResponse -> {
                                                acquireRequestResponseMap.remove(response.reflection)
                                                    ?.complete(response)
                                                    ?: log.warn("No handler found for: ${response.reflection}")
                                            }

                                            is MsgReleaseResponse -> {
                                                releaseRequestResponseMap.remove(response.reflection)
                                                    ?.complete(response)
                                                    ?: log.warn("No handler found for: ${response.reflection}")
                                            }

                                            is MsgQueryResponse -> {
                                                queryRequestResponseMap.remove(response.reflection)?.complete(response)
                                                    ?: log.warn("No handler found for: ${response.reflection}")
                                            }

                                            is MsgFindIntersectResponse -> {
                                                findIntersectRequestResponseMap.remove(response.reflection)
                                                    ?.complete(response)
                                                    ?: log.warn("No handler found for: ${response.reflection}")
                                            }

                                            is MsgRequestNextResponse -> {
                                                requestNextRequestResponseMap.remove(response.reflection)
                                                    ?.complete(response)
                                                    ?: log.warn("No handler found for: ${response.reflection}")
                                            }

                                            is MsgSubmitTxResponse -> {
                                                submitTxResponseMap.remove(response.reflection)?.complete(response)
                                                    ?: log.warn("No handler found for: ${response.reflection}")
                                            }

                                            is MsgEvaluateTxResponse -> {
                                                evaluateTxResponseMap.remove(response.reflection)?.complete(response)
                                                    ?: log.warn("No handler found for: ${response.reflection}")
                                            }

                                            is MsgAwaitAcquireMempoolResponse -> {
                                                awaitAcquireResponseMap.remove(response.reflection)?.complete(response)
                                                    ?: log.warn("No handler found for: ${response.reflection}")
                                            }

                                            is MsgReleaseMempoolResponse -> {
                                                releaseMempoolResponseMap.remove(response.reflection)
                                                    ?.complete(response)
                                                    ?: log.warn("No handler found for: ${response.reflection}")
                                            }

                                            is MsgHasTxResponse -> {
                                                hasTxResponseMap.remove(response.reflection)?.complete(response)
                                                    ?: log.warn("No handler found for: ${response.reflection}")
                                            }

                                            is MsgSizeAndCapacityResponse -> {
                                                sizeAndCapacityResponseMap.remove(response.reflection)
                                                    ?.complete(response)
                                                    ?: log.warn("No handler found for: ${response.reflection}")
                                            }

                                            is MsgNextTxResponse -> {
                                                nextTxResponseMap.remove(response.reflection)?.complete(response)
                                                    ?: log.warn("No handler found for: ${response.reflection}")
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
                                            if (log.isDebugEnabled) {
                                                log.debug("request: $request")
                                            }
                                            when (request) {
                                                is MsgAcquire -> {
                                                    acquireRequestResponseMap[request.mirror] =
                                                        request.completableDeferred
                                                }

                                                is MsgRelease -> {
                                                    releaseRequestResponseMap[request.mirror] =
                                                        request.completableDeferred
                                                }

                                                is MsgQuery -> {
                                                    queryRequestResponseMap[request.mirror] =
                                                        request.completableDeferred
                                                }

                                                is MsgFindIntersect -> {
                                                    findIntersectRequestResponseMap[request.mirror] =
                                                        request.completableDeferred
                                                }

                                                is MsgRequestNext -> {
                                                    requestNextRequestResponseMap[request.mirror] =
                                                        request.completableDeferred
                                                }

                                                is MsgSubmitTx -> {
                                                    submitTxResponseMap[request.mirror] =
                                                        request.completableDeferred
                                                }

                                                is MsgEvaluateTx -> {
                                                    evaluateTxResponseMap[request.mirror] =
                                                        request.completableDeferred
                                                }

                                                is MsgAwaitAcquire -> {
                                                    awaitAcquireResponseMap[request.mirror] =
                                                        request.completableDeferred
                                                }

                                                is MsgReleaseMempool -> {
                                                    releaseMempoolResponseMap[request.mirror] =
                                                        request.completableDeferred
                                                }

                                                is MsgHasTx -> {
                                                    hasTxResponseMap[request.mirror] =
                                                        request.completableDeferred
                                                }

                                                is MsgSizeAndCapacity -> {
                                                    sizeAndCapacityResponseMap[request.mirror] =
                                                        request.completableDeferred
                                                }

                                                is MsgNextTx -> {
                                                    nextTxResponseMap[request.mirror] =
                                                        request.completableDeferred
                                                }
                                            }
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
                            }
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

    override suspend fun acquire(pointOrOrigin: PointOrOrigin, timeoutMs: Long): MsgAcquireResponse {
        assertConnected()
        val completableDeferred = CompletableDeferred<MsgAcquireResponse>()
        sendQueue.send(
            MsgAcquire(
                args = pointOrOrigin,
                mirror = "Acquire:${UUID.randomUUID()}",
                completableDeferred = completableDeferred
            )
        )
        return coroutineScope {
            withTimeout(timeoutMs) {
                completableDeferred.await()
            }
        }
    }

    override suspend fun release(timeoutMs: Long): MsgReleaseResponse {
        assertConnected()
        val completableDeferred = CompletableDeferred<MsgReleaseResponse>()
        sendQueue.send(
            MsgRelease(
                mirror = "Release:${UUID.randomUUID()}",
                completableDeferred = completableDeferred
            )
        )
        return coroutineScope {
            withTimeout(timeoutMs) {
                completableDeferred.await()
            }
        }
    }

    override suspend fun chainTip(timeoutMs: Long): MsgQueryResponse {
        assertConnected()
        val completableDeferred = CompletableDeferred<MsgQueryResponse>()
        sendQueue.send(
            MsgQuery(
                args = QueryChainTip(),
                mirror = "QueryChainTip:${UUID.randomUUID()}",
                completableDeferred = completableDeferred
            )
        )
        return coroutineScope {
            withTimeout(timeoutMs) {
                completableDeferred.await()
            }
        }
    }

    override suspend fun poolParameters(pools: List<String>, timeoutMs: Long): MsgQueryResponse {
        assertConnected()
        val completableDeferred = CompletableDeferred<MsgQueryResponse>()
        sendQueue.send(
            MsgQuery(
                args = QueryPoolParameters(PoolParameters(pools)),
                mirror = "QueryPoolParameters:${UUID.randomUUID()}",
                completableDeferred = completableDeferred
            )
        )
        return coroutineScope {
            withTimeout(timeoutMs) {
                completableDeferred.await()
            }
        }
    }

    override suspend fun blockHeight(timeoutMs: Long): MsgQueryResponse {
        assertConnected()
        val completableDeferred = CompletableDeferred<MsgQueryResponse>()
        sendQueue.send(
            MsgQuery(
                args = QueryBlockHeight(),
                mirror = "QueryBlockHeight:${UUID.randomUUID()}",
                completableDeferred = completableDeferred
            )
        )
        return coroutineScope {
            withTimeout(timeoutMs) {
                completableDeferred.await()
            }
        }
    }

    override suspend fun currentProtocolParameters(timeoutMs: Long): MsgQueryResponse {
        assertConnected()
        val completableDeferred = CompletableDeferred<MsgQueryResponse>()
        sendQueue.send(
            MsgQuery(
                args = QueryCurrentProtocolParameters(),
                mirror = "QueryCurrentProtocolParameters:${UUID.randomUUID()}",
                completableDeferred = completableDeferred
            )
        )
        return coroutineScope {
            withTimeout(timeoutMs) {
                completableDeferred.await()
            }
        }
    }

    override suspend fun currentEpoch(timeoutMs: Long): MsgQueryResponse {
        assertConnected()
        val completableDeferred = CompletableDeferred<MsgQueryResponse>()
        sendQueue.send(
            MsgQuery(
                args = QueryCurrentEpoch(),
                mirror = "QueryCurrentEpoch:${UUID.randomUUID()}",
                completableDeferred = completableDeferred
            )
        )
        return coroutineScope {
            withTimeout(timeoutMs) {
                completableDeferred.await()
            }
        }
    }

    override suspend fun poolIds(timeoutMs: Long): MsgQueryResponse {
        assertConnected()
        val completableDeferred = CompletableDeferred<MsgQueryResponse>()
        sendQueue.send(
            MsgQuery(
                args = QueryPoolIds(),
                mirror = "QueryPoolIds:${UUID.randomUUID()}",
                completableDeferred = completableDeferred
            )
        )
        return coroutineScope {
            withTimeout(timeoutMs) {
                completableDeferred.await()
            }
        }
    }

    override suspend fun delegationsAndRewards(stakeAddresses: List<String>, timeoutMs: Long): MsgQueryResponse {
        assertConnected()
        val completableDeferred = CompletableDeferred<MsgQueryResponse>()
        sendQueue.send(
            MsgQuery(
                args = QueryDelegationsAndRewards(DelegationsAndRewards(stakeAddresses)),
                mirror = "QueryDelegationsAndRewards:${UUID.randomUUID()}",
                completableDeferred = completableDeferred
            )
        )
        return coroutineScope {
            withTimeout(timeoutMs) {
                completableDeferred.await()
            }
        }
    }

    override suspend fun eraStart(timeoutMs: Long): MsgQueryResponse {
        assertConnected()
        val completableDeferred = CompletableDeferred<MsgQueryResponse>()
        sendQueue.send(
            MsgQuery(
                args = QueryEraStart(),
                mirror = "QueryEraStart:${UUID.randomUUID()}",
                completableDeferred = completableDeferred
            )
        )
        return coroutineScope {
            withTimeout(timeoutMs) {
                completableDeferred.await()
            }
        }
    }

    override suspend fun eraSummaries(timeoutMs: Long): MsgQueryResponse {
        assertConnected()
        val completableDeferred = CompletableDeferred<MsgQueryResponse>()
        sendQueue.send(
            MsgQuery(
                args = QueryEraSummaries(),
                mirror = "QueryEraSummaries:${UUID.randomUUID()}",
                completableDeferred = completableDeferred
            )
        )
        return coroutineScope {
            withTimeout(timeoutMs) {
                completableDeferred.await()
            }
        }
    }

    override suspend fun genesisConfig(timeoutMs: Long): MsgQueryResponse {
        assertConnected()
        val completableDeferred = CompletableDeferred<MsgQueryResponse>()
        sendQueue.send(
            MsgQuery(
                args = QueryGenesisConfig(),
                mirror = "QueryGenesisConfig:${UUID.randomUUID()}",
                completableDeferred = completableDeferred
            )
        )
        return coroutineScope {
            withTimeout(timeoutMs) {
                completableDeferred.await()
            }
        }
    }

    override suspend fun nonMyopicMemberRewards(
        inputs: List<NonMyopicMemberRewardsInput>,
        timeoutMs: Long
    ): MsgQueryResponse {
        assertConnected()
        val completableDeferred = CompletableDeferred<MsgQueryResponse>()
        sendQueue.send(
            MsgQuery(
                args = QueryNonMyopicMemberRewards(NonMyopicMemberRewardsInputs(inputs)),
                mirror = "QueryNonMyopicMemberRewards:${UUID.randomUUID()}",
                completableDeferred = completableDeferred
            )
        )
        return coroutineScope {
            withTimeout(timeoutMs) {
                completableDeferred.await()
            }
        }
    }

    override suspend fun proposedProtocolParameters(timeoutMs: Long): MsgQueryResponse {
        assertConnected()
        val completableDeferred = CompletableDeferred<MsgQueryResponse>()
        sendQueue.send(
            MsgQuery(
                args = QueryProposedProtocolParameters(),
                mirror = "QueryProposedProtocolParameters:${UUID.randomUUID()}",
                completableDeferred = completableDeferred
            )
        )
        return coroutineScope {
            withTimeout(timeoutMs) {
                completableDeferred.await()
            }
        }
    }

    override suspend fun stakeDistribution(timeoutMs: Long): MsgQueryResponse {
        assertConnected()
        val completableDeferred = CompletableDeferred<MsgQueryResponse>()
        sendQueue.send(
            MsgQuery(
                args = QueryStakeDistribution(),
                mirror = "QueryStakeDistribution:${UUID.randomUUID()}",
                completableDeferred = completableDeferred
            )
        )
        return coroutineScope {
            withTimeout(timeoutMs) {
                completableDeferred.await()
            }
        }
    }

    override suspend fun systemStart(timeoutMs: Long): MsgQueryResponse {
        assertConnected()
        val completableDeferred = CompletableDeferred<MsgQueryResponse>()
        sendQueue.send(
            MsgQuery(
                args = QuerySystemStart(),
                mirror = "QuerySystemStart:${UUID.randomUUID()}",
                completableDeferred = completableDeferred
            )
        )
        return coroutineScope {
            withTimeout(timeoutMs) {
                completableDeferred.await()
            }
        }
    }

    override suspend fun utxoByTxIn(filters: List<TxIn>, timeoutMs: Long): MsgQueryResponse {
        assertConnected()
        val completableDeferred = CompletableDeferred<MsgQueryResponse>()
        sendQueue.send(
            MsgQuery(
                args = QueryUtxoByTxIn(TxInFilters(filters)),
                mirror = "QueryUtxoByTxIn:${UUID.randomUUID()}",
                completableDeferred = completableDeferred
            )
        )
        return coroutineScope {
            withTimeout(timeoutMs) {
                completableDeferred.await()
            }
        }
    }

    override suspend fun awaitAcquireMempool(timeoutMs: Long): MsgAwaitAcquireMempoolResponse {
        assertConnected()
        val completableDeferred = CompletableDeferred<MsgAwaitAcquireMempoolResponse>()
        sendQueue.send(
            MsgAwaitAcquire(
                args = EmptyObject(),
                mirror = "AwaitAcquireMempool:${UUID.randomUUID()}",
                completableDeferred = completableDeferred
            )
        )
        return coroutineScope {
            withTimeout(timeoutMs) {
                completableDeferred.await()
            }
        }
    }

    override suspend fun releaseMempool(timeoutMs: Long): MsgReleaseMempoolResponse {
        assertConnected()
        val completableDeferred = CompletableDeferred<MsgReleaseMempoolResponse>()
        sendQueue.send(
            MsgReleaseMempool(
                args = EmptyObject(),
                mirror = "ReleaseMempool:${UUID.randomUUID()}",
                completableDeferred = completableDeferred
            )
        )
        return coroutineScope {
            withTimeout(timeoutMs) {
                completableDeferred.await()
            }
        }
    }

    override suspend fun hasTx(txId: String, timeoutMs: Long): MsgHasTxResponse {
        assertConnected()
        val completableDeferred = CompletableDeferred<MsgHasTxResponse>()
        sendQueue.send(
            MsgHasTx(
                args = HasTx(id = txId),
                mirror = "HasTx:${UUID.randomUUID()}",
                completableDeferred = completableDeferred
            )
        )
        return coroutineScope {
            withTimeout(timeoutMs) {
                completableDeferred.await()
            }
        }
    }

    override suspend fun sizeAndCapacity(timeoutMs: Long): MsgSizeAndCapacityResponse {
        assertConnected()
        val completableDeferred = CompletableDeferred<MsgSizeAndCapacityResponse>()
        sendQueue.send(
            MsgSizeAndCapacity(
                mirror = "SizeAndCapacity:${UUID.randomUUID()}",
                completableDeferred = completableDeferred
            )
        )
        return coroutineScope {
            withTimeout(timeoutMs) {
                completableDeferred.await()
            }
        }
    }

    override suspend fun nextTx(timeoutMs: Long): MsgNextTxResponse {
        assertConnected()
        val completableDeferred = CompletableDeferred<MsgNextTxResponse>()
        sendQueue.send(
            MsgNextTx(
                mirror = "NextTx:${UUID.randomUUID()}",
                completableDeferred = completableDeferred
            )
        )
        return coroutineScope {
            withTimeout(timeoutMs) {
                completableDeferred.await()
            }
        }
    }

    override suspend fun findIntersect(points: List<PointDetailOrOrigin>, timeoutMs: Long): MsgFindIntersectResponse {
        assertConnected()
        val completableDeferred = CompletableDeferred<MsgFindIntersectResponse>()
        sendQueue.send(
            MsgFindIntersect(
                args = FindIntersect(points),
                mirror = "MsgFindIntersect:${UUID.randomUUID()}",
                completableDeferred = completableDeferred
            )
        )
        return coroutineScope {
            withTimeout(timeoutMs) {
                completableDeferred.await()
            }
        }
    }

    override suspend fun requestNext(timeoutMs: Long): MsgRequestNextResponse {
        assertConnected()
        val completableDeferred = CompletableDeferred<MsgRequestNextResponse>()
        sendQueue.send(
            MsgRequestNext(
                mirror = "MsgRequestNext:${UUID.randomUUID()}",
                completableDeferred = completableDeferred
            )
        )
        return coroutineScope {
            withTimeout(timeoutMs) {
                completableDeferred.await()
            }
        }
    }

    override suspend fun submit(tx: String, timeoutMs: Long): MsgSubmitTxResponse {
        assertConnected()
        val completableDeferred = CompletableDeferred<MsgSubmitTxResponse>()
        sendQueue.send(
            MsgSubmitTx(
                args = SubmitTx(tx),
                mirror = "MsgSubmitTx:${UUID.randomUUID()}",
                completableDeferred = completableDeferred,
            )
        )
        return coroutineScope {
            withTimeout(timeoutMs) {
                completableDeferred.await()
            }
        }
    }

    override suspend fun evaluate(tx: String, timeoutMs: Long): MsgEvaluateTxResponse {
        assertConnected()
        val completableDeferred = CompletableDeferred<MsgEvaluateTxResponse>()
        sendQueue.send(
            MsgEvaluateTx(
                args = EvaluateTx(tx),
                mirror = "MsgEvaluateTx:${UUID.randomUUID()}",
                completableDeferred = completableDeferred,
            )
        )
        return coroutineScope {
            withTimeout(timeoutMs) {
                completableDeferred.await()
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
            requestResponseMaps.forEach outer@{ requestResponseMap ->
                requestResponseMap.keys.forEach inner@{ key ->
                    requestResponseMap.remove(key)?.completeExceptionally(it)
                }
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
        internal val json = Json {
            classDiscriminator = "methodname"
            encodeDefaults = true
            explicitNulls = true
            ignoreUnknownKeys = true
            isLenient = true
            serializersModule = SerializersModule {
                contextual(BigInteger::class, BigIntegerSerializer)
                contextual(BigDecimal::class, BigDecimalSerializer)
            }
        }
    }
}
