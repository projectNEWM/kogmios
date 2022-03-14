package io.projectnewm.kogmios

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.websocket.*
import io.ktor.http.*
import io.ktor.serialization.*
import io.ktor.util.reflect.*
import io.ktor.utils.io.charsets.*
import io.ktor.websocket.*
import io.projectnewm.kogmios.protocols.localstatequery.JsonWspRequest
import io.projectnewm.kogmios.protocols.localstatequery.MsgAcquire
import io.projectnewm.kogmios.utils.bigIntegerSerializerModule
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.slf4j.LoggerFactory
import kotlin.coroutines.CoroutineContext

class Client(private val websocketHost: String, private val websocketPort: Int) : CoroutineScope {

    private val log by lazy { LoggerFactory.getLogger(Client::class.java) }

    var isConnected = false

    private val sendQueue = MutableSharedFlow<JsonWspRequest>(extraBufferCapacity = Int.MAX_VALUE)

    @OptIn(ExperimentalCoroutinesApi::class)
    fun asyncConnect(): Deferred<Boolean> {
        log.info("start asyncConnect()")
        val result = CompletableDeferred<Boolean>()
        val client = HttpClient(CIO) {
            install(WebSockets) {
                contentConverter = object : WebsocketContentConverter {
                    override fun isApplicable(frame: Frame): Boolean = frame.frameType == FrameType.TEXT

                    override suspend fun serialize(charset: Charset, typeInfo: TypeInfo, value: Any): Frame {
                        log.info("serialize() - charset: $charset, typeInfo: $typeInfo, value: $value")
                        return Frame.Text(
                            when (value) {
                                is JsonWspRequest -> json.encodeToString(value)
                                else -> throw IllegalArgumentException("Unable to serialize ${value::class.java.canonicalName}")
                            }
                        )
                    }

                    override suspend fun deserialize(charset: Charset, typeInfo: TypeInfo, content: Frame): Any {
                        log.error("deserialize() - charset: $charset, typeInfo: $typeInfo, content: $content")
                        return content
                    }
                }
            }
        }
        launch {
            try {
                client.webSocket(method = HttpMethod.Get, host = websocketHost, port = websocketPort) {
                    val session = this
                    isConnected = true
                    result.complete(true)
                    try {
                        awaitAll(
                            // Receive Loop
                            async {
                                while (!session.incoming.isClosedForReceive) {
                                    val response = session.receiveDeserialized<Frame.Text>()
                                    log.info("response: ${response.readText()}")
                                }
                            },

                            // Send Loop
                            async {
                                while (!session.outgoing.isClosedForSend) {
                                    sendQueue.onEach { request ->
                                        log.info("request: $request")
                                        session.sendSerialized(request)
                                    }.collect()
                                    log.warn("Done collecting sendQueue")
                                }
                            }
                        )
                    } catch (e: Throwable) {
                        log.error("Websocket Loop Error!", e)
                    }

                    log.warn("Closing WebSocket...")
                    close(CloseReason(CloseReason.Codes.NORMAL, "Normal Disconnect"))
                    client.close()
                    isConnected = false
                }
            } catch (e: Throwable) {
                log.error("Websocket Error!", e)
                if (result.isActive) {
                    result.complete(false)
                }
            }
        }
        return result
    }

    suspend fun acquire() {
        assertConnected()
        sendQueue.emit(MsgAcquire())
    }

    /**
     * Disconnect from the Ogmios server
     */
    fun disconnect() {
        if (isConnected) {
            runBlocking {
                job.cancelChildren()
                job.cancelAndJoin()
            }
        }
    }

    @Throws(IllegalStateException::class)
    private fun assertConnected() {
        if (!isConnected) {
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
            serializersModule = bigIntegerSerializerModule
        }
    }
}