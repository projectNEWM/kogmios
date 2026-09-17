package io.newm.kogmios.protocols.messages

import com.fasterxml.jackson.annotation.JsonProperty

import com.fasterxml.jackson.annotation.JsonTypeName

import io.newm.kogmios.protocols.model.result.EvaluateTxResult

/**
 * Response that comes back from Ogmios after a MsgEvaluateTx message is sent.
 */

@JsonTypeName(MsgEvaluateTx.METHOD_EVALUATE_TX)
data class MsgEvaluateTxResponse(
    @param:JsonProperty(value = "result", required = true)
    @get:JsonProperty("result")
    override val result: EvaluateTxResult,
    @param:JsonProperty(value = "id", required = true)
    @get:JsonProperty("id")
    override val id: String,
) : JsonRpcSuccessResponse()
