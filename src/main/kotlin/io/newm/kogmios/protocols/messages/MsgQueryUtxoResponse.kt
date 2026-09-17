package io.newm.kogmios.protocols.messages

import com.fasterxml.jackson.annotation.JsonProperty

import com.fasterxml.jackson.annotation.JsonTypeName

import io.newm.kogmios.protocols.model.result.UtxoResult

/**
 * Response that comes back from Ogmios after a queryLedgerStateUtxo message is sent.
 */

@JsonTypeName(MsgQuery.METHOD_QUERY_LEDGER_STATE_UTXO)
data class MsgQueryUtxoResponse(
    @param:JsonProperty(value = "result", required = true)
    @get:JsonProperty("result")
    override val result: UtxoResult,
    @param:JsonProperty(value = "id", required = true)
    @get:JsonProperty("id")
    override val id: String,
) : JsonRpcSuccessResponse()
