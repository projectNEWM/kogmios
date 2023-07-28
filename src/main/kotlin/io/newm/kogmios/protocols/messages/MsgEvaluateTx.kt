package io.newm.kogmios.protocols.messages

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.UUID

/**
 * Evaluate the costs for a transaction
 */
@Serializable
data class MsgEvaluateTx(
    @SerialName("method")
    override val method: String = METHOD_EVALUATE_TX,
    @SerialName("params")
    val params: SubmitOrEvalTx,
    @SerialName("id")
    override val id: String = "$method: ${UUID.randomUUID()}",
) : JsonRpcRequest() {
    companion object {
        const val METHOD_EVALUATE_TX = "evaluateTransaction"
    }
}
