package io.projectnewm.kogmios.websocket

import io.projectnewm.kogmios.DefaultCoroutineExceptionHandler
import io.projectnewm.kogmios.protocols.localstatequery.JsonWspRequest
import io.projectnewm.kogmios.protocols.localstatequery.JsonWspResponse
import jakarta.websocket.*
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import org.glassfish.tyrus.client.ClientManager
import org.slf4j.LoggerFactory
import java.io.Closeable
import java.net.URI
import kotlin.coroutines.CoroutineContext

class WebsocketClient(
    private val contentConverter: WebsocketContentConverter,
    loggerName: String? = null
) : CoroutineScope, Closeable {

    private val log by lazy {
        if (loggerName == null) {
            LoggerFactory.getLogger(WebsocketClient::class.java)
        } else {
            LoggerFactory.getLogger(loggerName)
        }
    }

    private lateinit var client: ClientManager
    private lateinit var session: Session

    val incoming = Channel<JsonWspResponse>(Channel.BUFFERED)
    val outgoing = Channel<JsonWspRequest>(Channel.BUFFERED)

    fun connectAsync(host: String, port: Int, path: String? = null, scheme: String = "ws"): Deferred<Boolean> {
        val deferred = CompletableDeferred<Boolean>()
        client = ClientManager.createClient()
        client.connectToServer(
            object : Endpoint() {
                override fun onOpen(session: Session, config: EndpointConfig) {
                    session.addMessageHandler(object : MessageHandler.Whole<String> {
                        override fun onMessage(message: String) {
                            log.info("onMessage: $message")
                            val response = contentConverter.deserialize(message) as JsonWspResponse
                            launch {
                                incoming.send(response)
                            }
                        }
                    })
                    launch {
                        outgoing.consumeEach { send(it) }
                    }
                    this@WebsocketClient.session = session
                    deferred.complete(true)
                }

                override fun onError(session: Session, thr: Throwable) {
                    log.error("Websocket Error!", thr)
                    deferred.completeExceptionally(thr)
                }
            },
            ClientEndpointConfig.Builder.create().build(),
            URI("$scheme://$host:$port/$path")
        )
        return deferred
    }

    private suspend fun send(request: JsonWspRequest) {
        if (!::session.isInitialized) {
            throw IllegalStateException("WebsocketClient not connected!")
        }
        val completableDeferred = CompletableDeferred<SendResult>()
        val jsonString = contentConverter.serialize(request)
        log.info("send(): $jsonString")
        session.asyncRemote.sendText(jsonString) {
            if (it.isOK) {
                completableDeferred.complete(it)
            } else {
                completableDeferred.completeExceptionally(it.exception)
            }
        }
        completableDeferred.await()
    }

    override fun close() {
        if (::session.isInitialized) {
            session.close()
            client.shutdown()
            runBlocking {
                job.cancelAndJoin()
            }
        }
    }

    private val job by lazy { SupervisorJob() }
    override val coroutineContext: CoroutineContext by lazy {
        job + Dispatchers.IO + DefaultCoroutineExceptionHandler(log)
    }

}