package io.newm.kogmios.protocols.messages

import com.fasterxml.jackson.annotation.JsonProperty

import com.fasterxml.jackson.annotation.JsonTypeName

import io.newm.kogmios.protocols.model.result.ProposedProtocolParametersResult

/**
 * Response that comes back from Ogmios after a queryLedgerStateProposedProtocolParameters message is sent.
 */

@JsonTypeName(MsgQuery.METHOD_QUERY_LEDGER_STATE_PROPOSED_PROTOCOL_PARAMETERS)
data class MsgQueryProposedProtocolParametersResponse(
    @param:JsonProperty(value = "result", required = true)
    @get:JsonProperty("result")
    override val result: ProposedProtocolParametersResult,
    @param:JsonProperty(value = "id", required = true)
    @get:JsonProperty("id")
    override val id: String,
) : JsonRpcSuccessResponse()
