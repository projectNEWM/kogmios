package io.projectnewm.kogmios

import io.projectnewm.kogmios.protocols.localstatequery.MsgAcquireResponse
import io.projectnewm.kogmios.protocols.localstatequery.MsgQueryResponse
import io.projectnewm.kogmios.protocols.localstatequery.MsgReleaseResponse
import io.projectnewm.kogmios.protocols.model.Origin
import io.projectnewm.kogmios.protocols.model.PointOrOrigin

interface StateQueryClient : Client {
    /**
     * Acquire a point on the chain to query. <Optional> If this method is not called,
     * queries will use the blockchain tip.
     */
    suspend fun acquire(pointOrOrigin: PointOrOrigin = Origin()): MsgAcquireResponse

    /**
     * Release the previously acquired point on the chain.
     */
    suspend fun release(): MsgReleaseResponse

    /**
     * Get the most recent information about the chain's tip.
     */
    suspend fun chainTip(): MsgQueryResponse

}

fun createStateQueryClient(websocketHost: String, websocketPort: Int): StateQueryClient {
    return ClientImpl(websocketHost, websocketPort)
}