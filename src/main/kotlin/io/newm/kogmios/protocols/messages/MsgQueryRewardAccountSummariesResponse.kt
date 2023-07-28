package io.newm.kogmios.protocols.messages

import io.newm.kogmios.protocols.model.result.RewardAccountSummariesResult
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Response that comes back from Ogmios after a queryLedgerStateRewardAccountSummaries message is sent.
 */
@Serializable
@SerialName(MsgQuery.METHOD_QUERY_LEDGER_STATE_REWARD_ACCOUNT_SUMMARIES)
data class MsgQueryRewardAccountSummariesResponse(
    @SerialName("result")
    override val result: RewardAccountSummariesResult,
    @SerialName("id")
    override val id: String,
) : JsonRpcSuccessResponse(result)
