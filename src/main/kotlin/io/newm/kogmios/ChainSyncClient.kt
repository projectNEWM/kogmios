package io.newm.kogmios

import io.newm.kogmios.Client.Companion.DEFAULT_REQUEST_TIMEOUT_MS
import io.newm.kogmios.protocols.messages.MsgFindIntersectResponse
import io.newm.kogmios.protocols.messages.MsgNextBlockResponse
import io.newm.kogmios.protocols.model.PointDetailOrOrigin

/**
 * Client interface for syncing the blockchain.
 */
interface ChainSyncClient : Client {
    /**
     * Ask for an intersection between the server's local chain and the given points.
     */
    suspend fun findIntersect(
        points: List<PointDetailOrOrigin>,
        timeoutMs: Long = DEFAULT_REQUEST_TIMEOUT_MS,
    ): MsgFindIntersectResponse

    /**
     * Request the next block from the server
     */
    suspend fun nextBlock(timeoutMs: Long = DEFAULT_REQUEST_TIMEOUT_MS): MsgNextBlockResponse
}

fun createChainSyncClient(
    websocketHost: String,
    websocketPort: Int,
    secure: Boolean = false,
    ogmiosCompact: Boolean = false,
    loggerName: String? = null,
): ChainSyncClient {
    return ClientImpl(websocketHost, websocketPort, secure, ogmiosCompact, loggerName)
}
