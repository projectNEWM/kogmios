package io.projectnewm.kogmios

import io.projectnewm.kogmios.protocols.localstatequery.MsgAcquireResponse
import io.projectnewm.kogmios.protocols.localstatequery.MsgQueryResponse
import io.projectnewm.kogmios.protocols.localstatequery.MsgReleaseResponse
import io.projectnewm.kogmios.protocols.localstatequery.model.Origin
import io.projectnewm.kogmios.protocols.localstatequery.model.PointOrOrigin

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

    /**
     * Get the parameters for a given pool(s)
     */
    suspend fun poolParameters(pools: List<String>): MsgQueryResponse

    /**
     * Get the current block number of the network
     */
    suspend fun blockHeight(): MsgQueryResponse

    /**
     * Get the current network protocol parameters
     */
    suspend fun currentProtocolParameters(): MsgQueryResponse

    /**
     * Get the current network epoch
     */
    suspend fun currentEpoch(): MsgQueryResponse

    /**
     * Get the list of pool ids
     */
    suspend fun poolIds(): MsgQueryResponse

    /**
     * Get the rewards and delegation information for the given stake addresses.
     */
    suspend fun delegationsAndRewards(stakeAddresses: List<String>): MsgQueryResponse

    /**
     * Get the beginning of this era.
     */
    suspend fun eraStart(): MsgQueryResponse
}

fun createStateQueryClient(websocketHost: String, websocketPort: Int, loggerName: String? = null): StateQueryClient {
    return ClientImpl(websocketHost, websocketPort, loggerName)
}
