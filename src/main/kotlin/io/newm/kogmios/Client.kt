package io.newm.kogmios

import java.io.Closeable

/**
 * Base interface for all Kogmios clients
 */
interface Client : Closeable {
    /**
     * Open a connection to the Ogmios server. Must be called before other query methods
     * @return true if successfully connected
     */
    suspend fun connect(): Boolean

    /**
     * True if connect() has been successfully called for this Client.
     */
    val isConnected: Boolean

    /**
     * Shutdown and disconnect from the Ogmios server
     */
    fun shutdown()

    companion object {
        const val DEFAULT_REQUEST_TIMEOUT_MS = 10_000L
        const val LONG_REQUEST_TIMEOUT_MS = 180_000L // 3 minutes
        const val INSANE_REQUEST_TIMEOUT_MS = 300_000L // 5 minutes
        const val LUDICROUS_REQUEST_TIMEOUT_MS = 600_000L // 10 minutes
        const val INFINITE_REQUEST_TIMEOUT_MS = Long.MAX_VALUE
    }
}
