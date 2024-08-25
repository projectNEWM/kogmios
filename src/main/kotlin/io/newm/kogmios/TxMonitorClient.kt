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
    suspend fun acquireMempool(timeoutMs: Long = LONG_REQUEST_TIMEOUT_MS): MsgAcquireMempoolResponse

    /**
     * Release the mempool snapshot
     */
    suspend fun releaseMempool(timeoutMs: Long = DEFAULT_REQUEST_TIMEOUT_MS): MsgReleaseMempoolResponse

    /**
     * Check whether the mempool (acquired snapshot) has a given transaction in it.
     */
    suspend fun hasTransaction(
        txId: String,
        timeoutMs: Long = DEFAULT_REQUEST_TIMEOUT_MS
    ): MsgHasTransactionResponse

    /**
     * Get size and capacities of the mempool (acquired snapshot).
     */
    suspend fun sizeOfMempool(timeoutMs: Long = DEFAULT_REQUEST_TIMEOUT_MS): MsgSizeOfMempoolResponse

    /**
     * Request the next mempool transaction from an acquired snapshot.
     */
    suspend fun nextTransaction(timeoutMs: Long = DEFAULT_REQUEST_TIMEOUT_MS): MsgNextTransactionResponse
}

fun createTxMonitorClient(
    websocketHost: String,
    websocketPort: Int,
    secure: Boolean = false,
    ogmiosCompact: Boolean = false,
    loggerName: String? = null,
): TxMonitorClient = ClientImpl(websocketHost, websocketPort, secure, ogmiosCompact, loggerName)
