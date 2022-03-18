package io.projectnewm.kogmios

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.websocket.*
import io.ktor.http.*
import io.ktor.serialization.*
import io.ktor.util.reflect.*
import io.ktor.utils.io.charsets.*
import io.ktor.websocket.*
import io.projectnewm.kogmios.protocols.localstatequery.*
import io.projectnewm.kogmios.protocols.model.PointOrOrigin
import io.projectnewm.kogmios.protocols.model.QueryChainTip
import io.projectnewm.kogmios.utils.bigIntegerSerializerModule
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ClosedReceiveChannelException
import kotlinx.coroutines.channels.ClosedSendChannelException
import kotlinx.coroutines.channels.consumeEach
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
    private val sendClose = CompletableDeferred<Unit>()
    private val receiveClose = CompletableDeferred<Unit>()

    private val sendQueue = Channel<JsonWspRequest>(Channel.BUFFERED)

    private lateinit var acquireCompletableDeferred: CompletableDeferred<MsgAcquireResponse>
    private lateinit var releaseCompletableDeferred: CompletableDeferred<MsgReleaseResponse>
    private lateinit var queryCompletableDeferred: CompletableDeferred<MsgQueryResponse>

    private lateinit var session: DefaultClientWebSocketSession
    private val httpClient by lazy {
        HttpClient(CIO) {
//            engine {
//                config {
//                    readTimeout(Duration.ofMillis(10_000L))
//                    writeTimeout(Duration.ofMillis(10_000L))
//                    callTimeout(Duration.ofMillis(20_000L))
//                    connectTimeout(Duration.ofMillis(10_000L))
//                }
//            }
            install(WebSockets) {
                contentConverter = object : WebsocketContentConverter {
                    override fun isApplicable(frame: Frame): Boolean = frame.frameType == FrameType.TEXT

                    override suspend fun serialize(charset: Charset, typeInfo: TypeInfo, value: Any): Frame {
                        if (log.isTraceEnabled) {
                            log.trace("serialize() - charset: $charset, typeInfo: $typeInfo, value: $value")
                        }
                        return Frame.Text(
                            when (value) {
                                is JsonWspRequest -> json.encodeToString(value)
                                else -> throw IllegalArgumentException("Unable to serialize ${value::class.java.canonicalName}")
                            }
                        )
                    }

                    override suspend fun deserialize(charset: Charset, typeInfo: TypeInfo, content: Frame): Any {
                        if (log.isTraceEnabled) {
                            log.trace("deserialize() - charset: $charset, typeInfo: $typeInfo, content: $content")
                        }
                        val jsonString = (content as Frame.Text).readText()
                        log.info(jsonString)
                        return json.decodeFromString<JsonWspResponse>(jsonString)
                    }
                }
            }
        }
    }

    override suspend fun connect(): Boolean {
        log.info("start connect()")
        return connectInternal().await()
    }

    private fun connectInternal(): CompletableDeferred<Boolean> {
        val result = CompletableDeferred<Boolean>()
        launch {
            httpClient.webSocket(
                method = HttpMethod.Get,
                host = websocketHost,
                port = websocketPort
            ) {
                session = this
                _isConnected = true
                result.complete(true)
                awaitAll(
                    // Receive Loop
                    async {
                        while (!session.incoming.isClosedForReceive) {
                            try {
                                val response = session.receiveDeserialized<JsonWspResponse>()
                                log.info("response: $response")
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
                        receiveClose.complete(Unit)
                    },

                    // Send Loop
                    async {
                        while (!session.outgoing.isClosedForSend) {
                            try {
                                sendQueue.consumeEach { request ->
                                    log.info("request: $request")
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
                                    session.sendSerialized(request)
                                }
                            } catch (e: ClosedSendChannelException) {
                                if (isClosing) {
                                    log.info("websocketClient.outgoing was closed.")
                                } else {
                                    log.error("websocketClient.outgoing was closed.", e)
                                }
                            }
                        }
                        sendClose.complete(Unit)
                    }
                )
                log.info("Websocket completed normally.")
            }
        }
        return result
    }

    override suspend fun acquire(pointOrOrigin: PointOrOrigin): MsgAcquireResponse {
        assertConnected()
        val deferred = CompletableDeferred<MsgAcquireResponse>()
        sendQueue.send(MsgAcquire(pointOrOrigin, deferred))
        return deferred.await()
    }

    override suspend fun release(): MsgReleaseResponse {
        assertConnected()
        val deferred = CompletableDeferred<MsgReleaseResponse>()
        sendQueue.send(MsgRelease(deferred))
        return deferred.await()
    }

    override suspend fun chainTip(): MsgQueryResponse {
        assertConnected()
        val deferred = CompletableDeferred<MsgQueryResponse>()
        sendQueue.send(MsgQuery(QueryChainTip(), deferred))
        return deferred.await()
    }

    /**
     * Disconnect from the Ogmios server
     */
    override fun shutdown() {
        if (_isConnected) {
            runBlocking {
                isClosing = true
                log.info("Closing WebSocket...")
                session.close(CloseReason(CloseReason.Codes.NORMAL, "Ok"))
                receiveClose.await()
                sendQueue.close()
                sendClose.await()
                httpClient.close()
                job.cancelAndJoin()
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