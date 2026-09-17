package io.newm.kogmios.protocols.messages

import com.fasterxml.jackson.annotation.JsonProperty

import java.util.UUID

/**
 * Evaluate the costs for a transaction
 */

data class MsgEvaluateTx(
    override val method: String = METHOD_EVALUATE_TX,
    @param:JsonProperty(value = "params", required = true)
    @get:JsonProperty("params")
    val params: SubmitOrEvalTx,
    override val id: String = "$method: ${UUID.randomUUID()}",
) : JsonRpcRequest() {
    companion object {
        const val METHOD_EVALUATE_TX = "evaluateTransaction"
    }
}
