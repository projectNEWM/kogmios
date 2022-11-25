package io.newm.kogmios.protocols.messages

import kotlinx.coroutines.CompletableDeferred
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Evaluate the costs for a transaction
 */
@Serializable
@SerialName("EvaluateTx")
data class MsgEvaluateTx(
    @SerialName("mirror")
    override val mirror: String,
    @SerialName("args")
    val args: EvaluateTx,
    @kotlinx.serialization.Transient
    val completableDeferred: CompletableDeferred<MsgEvaluateTxResponse> = CompletableDeferred(),
) : JsonWspRequest()

@Serializable
data class EvaluateTx(
    @SerialName("evaluate")
    val evaluate: String
)
// JSON Example
// {
//    "type": "jsonwsp/request",
//    "version": "1.0",
//    "servicename": "ogmios",
//    "methodname": "EvaluateTx",
//    "args": {
//      "evaluate": "1092304a09875d008..."
//    }
// }
