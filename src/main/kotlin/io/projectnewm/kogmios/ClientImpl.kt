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
) : CoroutineScope, StateQueryClient {

    private val log by lazy { LoggerFactory.getLogger(ClientImpl::class.java) }

    private var _isConnected = false
    override val isConnected: Boolean
        get() = _isConnected

    private val sendQueue = MutableSharedFlow<JsonWspRequest>(extraBufferCapacity = Int.MAX_VALUE)

    private lateinit var acquireCompletableDeferred: CompletableDeferred<MsgAcquireResponse>
    private lateinit var releaseCompletableDeferred: CompletableDeferred<MsgReleaseResponse>
    private lateinit var queryCompletableDeferred: CompletableDeferred<MsgQueryResponse>

    private val httpClient by lazy {
        HttpClient(CIO) {
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
        log.trace("start connect()")
        return connectInternal().await()
    }

    private fun connectInternal(): CompletableDeferred<Boolean> {
        val result = CompletableDeferred<Boolean>()
        launch {
            httpClient.webSocket(method = HttpMethod.Get, host = websocketHost, port = websocketPort) {
                val session = this
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
                            } catch (_: ClosedReceiveChannelException) {
                                log.warn("session.incoming was closed.")
                            }
                        }
                    },

                    // Send Loop
                    async {
                        while (!session.outgoing.isClosedForSend) {
                            try {
                                sendQueue.onEach { request ->
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
                                }.collect()
                            } catch (_: ClosedSendChannelException) {
                                log.warn("session.outgoing was closed.")
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
            log.warn("Closing WebSocket...")
            httpClient.close()
            runBlocking {
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