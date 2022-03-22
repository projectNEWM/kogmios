package io.projectnewm.kogmios

import io.projectnewm.kogmios.protocols.localstatequery.*
import io.projectnewm.kogmios.protocols.model.PointOrOrigin
import io.projectnewm.kogmios.protocols.model.QueryChainTip
import io.projectnewm.kogmios.utils.bigIntegerSerializerModule
import io.projectnewm.kogmios.websocket.WebsocketClient
import io.projectnewm.kogmios.websocket.WebsocketContentConverter
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ClosedReceiveChannelException
import kotlinx.coroutines.channels.ClosedSendChannelException
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import org.slf4j.LoggerFactory
import kotlin.coroutines.CoroutineContext

internal class ClientImpl(
    private val websocketHost: String,
    private val websocketPort: Int,
    loggerName: String? = null,
) : CoroutineScope, StateQueryClient {

    private val log by lazy {
        if (loggerName == null) {
            LoggerFactory.getLogger(ClientImpl::class.java)
        } else {
            LoggerFactory.getLogger(loggerName)
        }
    }

    private var _isConnected = false
    override val isConnected: Boolean
        get() = _isConnected
    private var isClosing = false

    private val sendQueue = MutableSharedFlow<JsonWspRequest>(extraBufferCapacity = Int.MAX_VALUE)

    private lateinit var acquireCompletableDeferred: CompletableDeferred<MsgAcquireResponse>
    private lateinit var releaseCompletableDeferred: CompletableDeferred<MsgReleaseResponse>
    private lateinit var queryCompletableDeferred: CompletableDeferred<MsgQueryResponse>

    private val websocketClient = WebsocketClient(object : WebsocketContentConverter {
        override fun serialize(value: Any): String {
            return when (value) {
                is JsonWspRequest -> json.encodeToString(value)
                else -> throw IllegalArgumentException("Unable to serialize ${value::class.java.canonicalName}")
            }
        }

        override fun deserialize(value: String): Any {
            return json.decodeFromString<JsonWspResponse>(value)
        }
    }, loggerName)

    override suspend fun connect(): Boolean {
        log.info("start connect()")
        return connectInternal().await()
    }

    private fun connectInternal(): CompletableDeferred<Boolean> {
        val result = CompletableDeferred<Boolean>()
        launch {
            log.info("open websocketClient")
            if (websocketClient.connectAsync(websocketHost, websocketPort).await()) {
                _isConnected = true
                result.complete(true)
                awaitAll(
                    // Receive Loop
                    async {
                        while (!websocketClient.incoming.isClosedForReceive) {
                            try {
                                val response = websocketClient.incoming.receive()
                                when (response) {
                                    is MsgAcquireResponse -> acquireCompletableDeferred.complete(response)
                                    is MsgReleaseResponse -> releaseCompletableDeferred.complete(response)
                                    is MsgQueryResponse -> queryCompletableDeferred.complete(response)
                                }
                            } catch (e: ClosedReceiveChannelException) {
                                if (isClosing) {
                                    log.info("websocketClient.incoming was closed.")
                                } else {
                                    log.error("websocketClient.incoming was closed.", e)
                                }
                            }
                        }
                    },
                    // Send Loop
                    async {
                        while (!websocketClient.outgoing.isClosedForSend) {
                            try {
                                sendQueue.onEach { request ->
                                    when (request) {
                                        is MsgAcquire -> {
                                            acquireCompletableDeferred = request.completableDeferred
                                        }
                                        is MsgRelease -> {
                                            releaseCompletableDeferred = request.completableDeferred
                                        }
                                        is MsgQuery -> {
                                            queryCompletableDeferred = request.completableDeferred
                                        }
                                    }
                                    websocketClient.outgoing.send(request)
                                }.collect()
                            } catch (e: ClosedSendChannelException) {
                                if (isClosing) {
                                    log.info("websocketClient.outgoing was closed.")
                                } else {
                                    log.error("websocketClient.outgoing was closed.", e)
                                }
                            }
                        }
                    }
                )
            }
        }
        return result
    }

    override suspend fun acquire(pointOrOrigin: PointOrOrigin): MsgAcquireResponse {
        assertConnected()
        val deferred = CompletableDeferred<MsgAcquireResponse>()
        sendQueue.emit(MsgAcquire(pointOrOrigin, deferred))
        return deferred.await()
    }

    override suspend fun release(): MsgReleaseResponse {
        assertConnected()
        val deferred = CompletableDeferred<MsgReleaseResponse>()
        sendQueue.emit(MsgRelease(deferred))
        return deferred.await()
    }

    override suspend fun chainTip(): MsgQueryResponse {
        assertConnected()
        val deferred = CompletableDeferred<MsgQueryResponse>()
        sendQueue.emit(MsgQuery(QueryChainTip(), deferred))
        return deferred.await()
    }

    /**
     * Disconnect from the Ogmios server
     */
    override fun shutdown() {
        if (_isConnected) {
            runBlocking {
                isClosing = true
                log.warn("Closing WebSocket...")
                websocketClient.close()
                log.warn("Closing WebSocket...done")
                log.warn("cancelAndJoin()")
                job.cancelAndJoin()
                log.warn("cancelAndJoin() done")
            }
            _isConnected = false
        }
    }

    @Throws(IllegalStateException::class)
    private fun assertConnected() {
        if (!_isConnected) {
            throw IllegalStateException("Kogmios client must be connected! Did you forget to call connect()?")
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
            serializersModule = SerializersModule {
                include(bigIntegerSerializerModule)
            }
        }
    }
}