package io.newm.kogmios.protocols.messages

import com.fasterxml.jackson.annotation.JsonProperty

import com.fasterxml.jackson.annotation.JsonTypeName

import io.newm.kogmios.protocols.model.result.LiveStakeDistributionResult

/**
 * Response that comes back from Ogmios after a queryLedgerStateLiveStakeDistribution message is sent.
 */

@JsonTypeName(MsgQuery.METHOD_QUERY_LEDGER_STATE_LIVE_STAKE_DISTRIBUTION)
data class MsgQueryLiveStakeDistributionResponse(
    @param:JsonProperty(value = "result", required = true)
    @get:JsonProperty("result")
    override val result: LiveStakeDistributionResult,
    @param:JsonProperty(value = "id", required = true)
    @get:JsonProperty("id")
    override val id: String,
) : JsonRpcSuccessResponse()
