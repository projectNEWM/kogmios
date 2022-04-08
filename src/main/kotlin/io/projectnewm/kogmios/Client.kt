package io.projectnewm.kogmios

/**
 * Base interface for all Kogmios clients
 */
interface Client {
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
}
