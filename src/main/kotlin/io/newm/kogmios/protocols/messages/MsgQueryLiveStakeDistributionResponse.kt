package io.newm.kogmios.protocols.messages

import io.newm.kogmios.protocols.model.result.LiveStakeDistributionResult
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Response that comes back from Ogmios after a queryLedgerStateLiveStakeDistribution message is sent.
 */
@Serializable
@SerialName(MsgQuery.METHOD_QUERY_LEDGER_STATE_LIVE_STAKE_DISTRIBUTION)
data class MsgQueryLiveStakeDistributionResponse(
    @SerialName("result")
    override val result: LiveStakeDistributionResult,
    @SerialName("id")
    override val id: String,
) : JsonRpcSuccessResponse(result)
