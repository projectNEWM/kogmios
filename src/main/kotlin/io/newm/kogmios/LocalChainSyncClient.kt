package io.projectnewm.kogmios

import io.projectnewm.kogmios.protocols.messages.MsgFindIntersectResponse
import io.projectnewm.kogmios.protocols.messages.MsgRequestNextResponse
import io.projectnewm.kogmios.protocols.model.PointDetailOrOrigin

interface LocalChainSyncClient : Client {

    /**
     * Ask for an intersection between the server's local chain and the given points.
     */
    suspend fun findIntersect(points: List<PointDetailOrOrigin>): MsgFindIntersectResponse

    /**
     * Request the next block from the server
     */
    suspend fun requestNext(): MsgRequestNextResponse
}

fun createLocalChainSyncClient(
    websocketHost: String,
    websocketPort: Int,
    ogmiosCompact: Boolean = false,
    loggerName: String? = null
): LocalChainSyncClient {
    return ClientImpl(websocketHost, websocketPort, ogmiosCompact, loggerName)
}
