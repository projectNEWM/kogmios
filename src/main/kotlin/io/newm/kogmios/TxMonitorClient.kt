package io.newm.kogmios

import io.newm.kogmios.Client.Companion.DEFAULT_REQUEST_TIMEOUT_MS
import io.newm.kogmios.protocols.messages.JsonWspResponse

interface TxMonitorClient : Client {
//    /**
//     * Acquire the mempool
//     */
//    suspend fun acquire(): MsgAcquireResponse
//
//    /**
//     * Release the mempool
//     */
//    suspend fun release(): MsgReleaseResponse

    suspend fun hasTx(txId: String, timeoutMs: Long = DEFAULT_REQUEST_TIMEOUT_MS): JsonWspResponse
}

fun createTxMonitorClient(
    websocketHost: String,
    websocketPort: Int,
    secure: Boolean = false,
    ogmiosCompact: Boolean = false,
    loggerName: String? = null
): TxMonitorClient {
    return ClientImpl(websocketHost, websocketPort, secure, ogmiosCompact, loggerName)
}
