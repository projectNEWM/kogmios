package io.newm.kogmios

import io.newm.kogmios.Client.Companion.DEFAULT_REQUEST_TIMEOUT_MS
import io.newm.kogmios.Client.Companion.LONG_REQUEST_TIMEOUT_MS
import io.newm.kogmios.protocols.messages.*

/**
 * Client interface for working with the node's mempool.
 */
interface TxMonitorClient : Client {
    /**
     * Acquire the mempool snapshot
     */
    suspend fun awaitAcquireMempool(timeoutMs: Long = LONG_REQUEST_TIMEOUT_MS): MsgAwaitAcquireMempoolResponse

    /**
     * Release the mempool snapshot
     */
    suspend fun releaseMempool(timeoutMs: Long = DEFAULT_REQUEST_TIMEOUT_MS): MsgReleaseMempoolResponse

    /**
     * Check whether the mempool (acquired snapshot) has a given transaction in it.
     */
    suspend fun hasTx(txId: String, timeoutMs: Long = DEFAULT_REQUEST_TIMEOUT_MS): MsgHasTxResponse

    /**
     * Get size and capacities of the mempool (acquired snapshot).
     */
    suspend fun sizeAndCapacity(timeoutMs: Long = DEFAULT_REQUEST_TIMEOUT_MS): MsgSizeAndCapacityResponse

    /**
     * Request the next mempool transaction from an acquired snapshot.
     */
    suspend fun nextTx(timeoutMs: Long = DEFAULT_REQUEST_TIMEOUT_MS): MsgNextTxResponse
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
