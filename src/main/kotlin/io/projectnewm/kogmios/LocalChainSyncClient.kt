package io.projectnewm.kogmios

import io.projectnewm.kogmios.protocols.localstatequery.MsgFindIntersectResponse
import io.projectnewm.kogmios.protocols.localstatequery.model.PointDetailOrOrigin

interface LocalChainSyncClient : Client {

    /**
     * Ask for an intersection between the server's local chain and the given points.
     */
    suspend fun findIntersect(points: List<PointDetailOrOrigin>): MsgFindIntersectResponse
}

fun createLocalChainSyncClient(
    websocketHost: String,
    websocketPort: Int,
    loggerName: String? = null
): LocalChainSyncClient {
    return ClientImpl(websocketHost, websocketPort, loggerName)
}
