package io.newm.kogmios.protocols.messages

import com.fasterxml.jackson.annotation.JsonProperty

import com.fasterxml.jackson.annotation.JsonTypeName

import io.newm.kogmios.protocols.model.result.SubmitTxResult

/**
 * Response that comes back from Ogmios after a submitTx message is sent.
 */

@JsonTypeName(MsgSubmitTx.METHOD_SUBMIT_TX)
data class MsgSubmitTxResponse(
    @param:JsonProperty(value = "result", required = true)
    @get:JsonProperty("result")
    override val result: SubmitTxResult,
    @param:JsonProperty(value = "id", required = true)
    @get:JsonProperty("id")
    override val id: String,
) : JsonRpcSuccessResponse()
