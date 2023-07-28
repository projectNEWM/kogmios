package io.newm.kogmios

import io.newm.kogmios.Client.Companion.DEFAULT_REQUEST_TIMEOUT_MS
import io.newm.kogmios.Client.Companion.INSANE_REQUEST_TIMEOUT_MS
import io.newm.kogmios.Client.Companion.LONG_REQUEST_TIMEOUT_MS
import io.newm.kogmios.protocols.messages.MsgAcquireResponse
import io.newm.kogmios.protocols.messages.MsgQueryBlockHeightResponse
import io.newm.kogmios.protocols.messages.MsgQueryEpochResponse
import io.newm.kogmios.protocols.messages.MsgQueryEraStartResponse
import io.newm.kogmios.protocols.messages.MsgQueryEraSummariesResponse
import io.newm.kogmios.protocols.messages.MsgQueryGenesisConfigResponse
import io.newm.kogmios.protocols.messages.MsgQueryLiveStakeDistributionResponse
import io.newm.kogmios.protocols.messages.MsgQueryNetworkStartTimeResponse
import io.newm.kogmios.protocols.messages.MsgQueryProjectedRewardsResponse
import io.newm.kogmios.protocols.messages.MsgQueryProposedProtocolParametersResponse
import io.newm.kogmios.protocols.messages.MsgQueryProtocolParametersResponse
import io.newm.kogmios.protocols.messages.MsgQueryRewardAccountSummariesResponse
import io.newm.kogmios.protocols.messages.MsgQueryStakePoolsResponse
import io.newm.kogmios.protocols.messages.MsgQueryTipResponse
import io.newm.kogmios.protocols.messages.MsgQueryUtxoResponse
import io.newm.kogmios.protocols.messages.MsgReleaseResponse
import io.newm.kogmios.protocols.model.GenesisEra
import io.newm.kogmios.protocols.model.Origin
import io.newm.kogmios.protocols.model.ParamsProjectedRewards
import io.newm.kogmios.protocols.model.ParamsUtxo
import io.newm.kogmios.protocols.model.PointOrOrigin

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
    suspend fun chainTip(timeoutMs: Long = DEFAULT_REQUEST_TIMEOUT_MS): MsgQueryTipResponse

    /**
     * Get the information for a given pool(s)
     */
    suspend fun stakePools(
        pools: List<String>,
        timeoutMs: Long = LONG_REQUEST_TIMEOUT_MS
    ): MsgQueryStakePoolsResponse

    /**
     * Get the current block number of the network
     */
    suspend fun blockHeight(timeoutMs: Long = DEFAULT_REQUEST_TIMEOUT_MS): MsgQueryBlockHeightResponse

    /**
     * Get the current network protocol parameters
     */
    suspend fun protocolParameters(timeoutMs: Long = DEFAULT_REQUEST_TIMEOUT_MS): MsgQueryProtocolParametersResponse

    /**
     * Get the current network epoch
     */
    suspend fun epoch(timeoutMs: Long = DEFAULT_REQUEST_TIMEOUT_MS): MsgQueryEpochResponse

    /**
     * Get the rewards and delegation information for the given stake addresses.
     */
    suspend fun rewardAccountSummaries(
        stakeAddresses: List<String>,
        timeoutMs: Long = LONG_REQUEST_TIMEOUT_MS
    ): MsgQueryRewardAccountSummariesResponse

    /**
     * Get the beginning of this era.
     */
    suspend fun eraStart(timeoutMs: Long = DEFAULT_REQUEST_TIMEOUT_MS): MsgQueryEraStartResponse

    /**
     * Get summaries of all Cardano eras, necessary to do proper slotting arithmetic.
     */
    suspend fun eraSummaries(timeoutMs: Long = DEFAULT_REQUEST_TIMEOUT_MS): MsgQueryEraSummariesResponse

    /**
     * Get the genesis configuration for any era: "byron", "shelley", "alonzo", "conway"
     */
    suspend fun genesisConfig(
        era: GenesisEra,
        timeoutMs: Long = DEFAULT_REQUEST_TIMEOUT_MS
    ): MsgQueryGenesisConfigResponse

    /**
     * Query the projected rewards of an account in a context where the top stake pools are fully saturated.
     * This projection gives, in principle, a ranking of stake pools that maximizes delegator rewards.
     */
    suspend fun projectedRewards(
        params: ParamsProjectedRewards,
        timeoutMs: Long = LONG_REQUEST_TIMEOUT_MS
    ): MsgQueryProjectedRewardsResponse

    /**
     * Get proposed protocol parameters for update, if any.
     */
    suspend fun proposedProtocolParameters(timeoutMs: Long = DEFAULT_REQUEST_TIMEOUT_MS): MsgQueryProposedProtocolParametersResponse

    /**
     * Get the current stake pool distribution. This request may be quite long, use with care.
     */
    suspend fun liveStakeDistribution(timeoutMs: Long = INSANE_REQUEST_TIMEOUT_MS): MsgQueryLiveStakeDistributionResponse

    /**
     * Get the start date of the network.
     */
    suspend fun networkStartTime(timeoutMs: Long = DEFAULT_REQUEST_TIMEOUT_MS): MsgQueryNetworkStartTimeResponse

    /**
     * Queries the Utxo details associated with some TxIn value(s)
     */
    suspend fun utxo(
        params: ParamsUtxo,
        timeoutMs: Long = DEFAULT_REQUEST_TIMEOUT_MS
    ): MsgQueryUtxoResponse
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
