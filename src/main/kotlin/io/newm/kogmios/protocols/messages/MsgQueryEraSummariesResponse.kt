package io.newm.kogmios.protocols.messages

import com.fasterxml.jackson.annotation.JsonProperty

import com.fasterxml.jackson.annotation.JsonTypeName

import io.newm.kogmios.protocols.model.result.EraSummariesResult

/**
 * Response that comes back from Ogmios after a queryLedgerStateEraSummaries message is sent.
 */

@JsonTypeName(MsgQuery.METHOD_QUERY_LEDGER_STATE_ERA_SUMMARIES)
data class MsgQueryEraSummariesResponse(
    @param:JsonProperty(value = "result", required = true)
    @get:JsonProperty("result")
    override val result: EraSummariesResult,
    @param:JsonProperty(value = "id", required = true)
    @get:JsonProperty("id")
    override val id: String,
) : JsonRpcSuccessResponse()
