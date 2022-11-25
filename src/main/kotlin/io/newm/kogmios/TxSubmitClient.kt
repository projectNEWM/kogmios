package io.newm.kogmios

import io.newm.kogmios.Client.Companion.DEFAULT_REQUEST_TIMEOUT_MS
import io.newm.kogmios.protocols.messages.MsgEvaluateTxResponse
import io.newm.kogmios.protocols.messages.MsgSubmitTxResponse

interface TxSubmitClient : Client {
    suspend fun submit(tx: String, timeoutMs: Long = DEFAULT_REQUEST_TIMEOUT_MS): MsgSubmitTxResponse
    suspend fun evaluate(tx: String, timeoutMs: Long = DEFAULT_REQUEST_TIMEOUT_MS): MsgEvaluateTxResponse
}

fun createTxSubmitClient(
    websocketHost: String,
    websocketPort: Int,
    secure: Boolean = false,
    ogmiosCompact: Boolean = false,
    loggerName: String? = null
): TxSubmitClient {
    return ClientImpl(websocketHost, websocketPort, secure, ogmiosCompact, loggerName)
}
