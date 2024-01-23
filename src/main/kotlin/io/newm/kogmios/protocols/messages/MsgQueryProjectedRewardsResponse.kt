package io.newm.kogmios.protocols.messages

import io.newm.kogmios.protocols.model.result.ProjectedRewardsResult
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Response that comes back from Ogmios after a queryLedgerStateProjectedRewards message is sent.
 */
@Serializable
@SerialName(MsgQuery.METHOD_QUERY_LEDGER_STATE_PROJECTED_REWARDS)
data class MsgQueryProjectedRewardsResponse(
    @SerialName("result")
    override val result: ProjectedRewardsResult,
    @SerialName("id")
    override val id: String,
) : JsonRpcSuccessResponse(result)
