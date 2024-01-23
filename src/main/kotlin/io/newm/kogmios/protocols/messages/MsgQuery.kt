package io.newm.kogmios.protocols.messages

import io.newm.kogmios.protocols.model.Params
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.UUID

/**
 * Message sent to query various ledger state items.
 */
@Serializable
data class MsgQuery(
    @SerialName("method")
    override val method: String,
    @SerialName("params")
    val params: Params? = null,
    @SerialName("id")
    override val id: String = "$method: ${UUID.randomUUID()}",
) : JsonRpcRequest() {
    companion object {
        const val METHOD_QUERY_NETWORK_BLOCK_HEIGHT = "queryNetwork/blockHeight"
        const val METHOD_QUERY_NETWORK_GENESIS_CONFIGURATION = "queryNetwork/genesisConfiguration"
        const val METHOD_QUERY_NETWORK_START_TIME = "queryNetwork/startTime"
        const val METHOD_QUERY_NETWORK_TIP = "queryNetwork/tip"
        const val METHOD_QUERY_LEDGER_STATE_EPOCH = "queryLedgerState/epoch"
        const val METHOD_QUERY_LEDGER_STATE_ERA_START = "queryLedgerState/eraStart"
        const val METHOD_QUERY_LEDGER_STATE_ERA_SUMMARIES = "queryLedgerState/eraSummaries"
        const val METHOD_QUERY_LEDGER_STATE_LIVE_STAKE_DISTRIBUTION = "queryLedgerState/liveStakeDistribution"
        const val METHOD_QUERY_LEDGER_STATE_PROJECTED_REWARDS = "queryLedgerState/projectedRewards"
        const val METHOD_QUERY_LEDGER_STATE_PROTOCOL_PARAMETERS = "queryLedgerState/protocolParameters"
        const val METHOD_QUERY_LEDGER_STATE_PROPOSED_PROTOCOL_PARAMETERS = "queryLedgerState/proposedProtocolParameters"
        const val METHOD_QUERY_LEDGER_STATE_REWARD_ACCOUNT_SUMMARIES = "queryLedgerState/rewardAccountSummaries"
        const val METHOD_QUERY_LEDGER_STATE_REWARDS_PROVENANCE = "queryLedgerState/rewardsProvenance"
        const val METHOD_QUERY_LEDGER_STATE_STAKE_POOLS = "queryLedgerState/stakePools"
        const val METHOD_QUERY_LEDGER_STATE_STAKE_POOL_PARAMETERS = "queryLedgerState/stakePoolParameters"

        // const val METHOD_QUERY_LEDGER_STATE_TIP = "queryLedgerState/tip"
        const val METHOD_QUERY_LEDGER_STATE_UTXO = "queryLedgerState/utxo"
    }
}
