package io.projectnewm.kogmios

import io.projectnewm.kogmios.protocols.messages.JsonWspResponse

interface LocalTxMonitorClient : Client {
//    /**
//     * Acquire the mempool
//     */
//    suspend fun acquire(): MsgAcquireResponse
//
//    /**
//     * Release the mempool
//     */
//    suspend fun release(): MsgReleaseResponse

    suspend fun hasTx(txId: String): JsonWspResponse
}

fun createLocalTxMonitorClient(
    websocketHost: String,
    websocketPort: Int,
    ogmiosCompact: Boolean = false,
    loggerName: String? = null
): LocalTxMonitorClient {
    return ClientImpl(websocketHost, websocketPort, ogmiosCompact, loggerName)
}
