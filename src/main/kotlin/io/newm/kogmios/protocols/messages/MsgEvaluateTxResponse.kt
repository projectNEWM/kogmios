package io.newm.kogmios.protocols.messages

import io.newm.kogmios.protocols.model.result.EvaluateTxResult
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Response that comes back from Ogmios after a MsgEvaluateTx message is sent.
 */
@Serializable
@SerialName(MsgEvaluateTx.METHOD_EVALUATE_TX)
data class MsgEvaluateTxResponse(
    @SerialName("result")
    override val result: EvaluateTxResult,
    @SerialName("id")
    override val id: String,
) : JsonRpcSuccessResponse()
