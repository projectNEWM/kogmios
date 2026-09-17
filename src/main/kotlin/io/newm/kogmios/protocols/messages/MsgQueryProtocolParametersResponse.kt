package io.newm.kogmios.protocols.messages

import com.fasterxml.jackson.annotation.JsonProperty

import com.fasterxml.jackson.annotation.JsonTypeName

import io.newm.kogmios.protocols.model.result.ProtocolParametersResult

/**
 * Response that comes back from Ogmios after a queryLedgerStateProtocolParameters message is sent.
 */

@JsonTypeName(MsgQuery.METHOD_QUERY_LEDGER_STATE_PROTOCOL_PARAMETERS)
data class MsgQueryProtocolParametersResponse(
    @param:JsonProperty(value = "result", required = true)
    @get:JsonProperty("result")
    override val result: ProtocolParametersResult,
    @param:JsonProperty(value = "id", required = true)
    @get:JsonProperty("id")
    override val id: String,
) : JsonRpcSuccessResponse()
