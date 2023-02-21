package io.newm.kogmios

import io.newm.kogmios.Client.Companion.DEFAULT_REQUEST_TIMEOUT_MS
import io.newm.kogmios.Client.Companion.INSANE_REQUEST_TIMEOUT_MS
import io.newm.kogmios.Client.Companion.LONG_REQUEST_TIMEOUT_MS
import io.newm.kogmios.protocols.messages.MsgAcquireResponse
import io.newm.kogmios.protocols.messages.MsgQueryResponse
import io.newm.kogmios.protocols.messages.MsgReleaseResponse
import io.newm.kogmios.protocols.model.NonMyopicMemberRewardsInput
import io.newm.kogmios.protocols.model.Origin
import io.newm.kogmios.protocols.model.PointOrOrigin
import io.newm.kogmios.protocols.model.TxIn

/**
 * Client interface for querying the state of the node and ledger.
 */
interface StateQueryClient : Client {
    /**
     * Acquire a point on the chain to query. <Optional> If this method is not called,
     * queries will use the blockchain tip.
     */
    suspend fun acquire(
        pointOrOrigin: PointOrOrigin = Origin(),
        timeoutMs: Long = DEFAULT_REQUEST_TIMEOUT_MS,
    ): MsgAcquireResponse

    /**
     * Release the previously acquired point on the chain.
     */
    suspend fun release(timeoutMs: Long = DEFAULT_REQUEST_TIMEOUT_MS): MsgReleaseResponse

    /**
     * Get the most recent information about the chain's tip.
     */
    suspend fun chainTip(timeoutMs: Long = DEFAULT_REQUEST_TIMEOUT_MS): MsgQueryResponse

    /**
     * Get the parameters for a given pool(s)
     */
    suspend fun poolParameters(pools: List<String>, timeoutMs: Long = LONG_REQUEST_TIMEOUT_MS): MsgQueryResponse

    /**
     * Get the current block number of the network
     */
    suspend fun blockHeight(timeoutMs: Long = DEFAULT_REQUEST_TIMEOUT_MS): MsgQueryResponse

    /**
     * Get the current network protocol parameters
     */
    suspend fun currentProtocolParameters(timeoutMs: Long = DEFAULT_REQUEST_TIMEOUT_MS): MsgQueryResponse

    /**
     * Get the current network epoch
     */
    suspend fun currentEpoch(timeoutMs: Long = DEFAULT_REQUEST_TIMEOUT_MS): MsgQueryResponse

    /**
     * Get the list of pool ids
     */
    suspend fun poolIds(timeoutMs: Long = LONG_REQUEST_TIMEOUT_MS): MsgQueryResponse

    /**
     * Get the rewards and delegation information for the given stake addresses.
     */
    suspend fun delegationsAndRewards(
        stakeAddresses: List<String>,
        timeoutMs: Long = LONG_REQUEST_TIMEOUT_MS,
    ): MsgQueryResponse

    /**
     * Get the beginning of this era.
     */
    suspend fun eraStart(timeoutMs: Long = DEFAULT_REQUEST_TIMEOUT_MS): MsgQueryResponse

    /**
     * Get summaries of all Cardano eras, necessary to do proper slotting arithmetic.
     */
    suspend fun eraSummaries(timeoutMs: Long = DEFAULT_REQUEST_TIMEOUT_MS): MsgQueryResponse

    /**
     * Get the genesis configuration.
     */
    suspend fun genesisConfig(genesisConfigEra: String = "shelley", timeoutMs: Long = DEFAULT_REQUEST_TIMEOUT_MS): MsgQueryResponse

    /**
     * Get non-myopic member rewards from a projected delegation amount;
     * this is used to rank pools such that the system converges towards
     * a fixed number of pools at equilibrium.
     */
    suspend fun nonMyopicMemberRewards(
        inputs: List<NonMyopicMemberRewardsInput>,
        timeoutMs: Long = LONG_REQUEST_TIMEOUT_MS,
    ): MsgQueryResponse

    /**
     * Get proposed protocol parameters for update, if any.
     */
    suspend fun proposedProtocolParameters(timeoutMs: Long = DEFAULT_REQUEST_TIMEOUT_MS): MsgQueryResponse

    /**
     * Get the current stake pool distribution. This request may be quite long, use with care.
     */
    suspend fun stakeDistribution(timeoutMs: Long = INSANE_REQUEST_TIMEOUT_MS): MsgQueryResponse

    /**
     * Get the start date of the network.
     */
    suspend fun systemStart(timeoutMs: Long = DEFAULT_REQUEST_TIMEOUT_MS): MsgQueryResponse

    /**
     * Queries the Utxo details associated with some TxIn value(s)
     */
    suspend fun utxoByTxIn(filters: List<TxIn>, timeoutMs: Long = DEFAULT_REQUEST_TIMEOUT_MS): MsgQueryResponse
}

fun createStateQueryClient(
    websocketHost: String,
    websocketPort: Int,
    secure: Boolean = false,
    ogmiosCompact: Boolean = false,
    loggerName: String? = null,
): StateQueryClient {
    return ClientImpl(websocketHost, websocketPort, secure, ogmiosCompact, loggerName)
}
