package io.newm.kogmios

import io.newm.kogmios.protocols.messages.MsgSubmitTxResponse

interface LocalTxSubmitClient : Client {
    suspend fun submit(tx: String): MsgSubmitTxResponse
//  todo  suspend fun evaluate(tx: String): MsgEvaluateTxResponse
}

fun createLocalTxSubmitClient(
    websocketHost: String,
    websocketPort: Int,
    ogmiosCompact: Boolean = false,
    loggerName: String? = null
): LocalTxSubmitClient {
    return ClientImpl(websocketHost, websocketPort, ogmiosCompact, loggerName)
}
