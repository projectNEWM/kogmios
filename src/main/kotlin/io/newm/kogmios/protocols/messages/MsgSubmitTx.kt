package io.newm.kogmios.protocols.messages

import kotlinx.coroutines.CompletableDeferred
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Submit a transaction to the node's mempool.
 */
@Serializable
@SerialName("SubmitTx")
data class MsgSubmitTx(
    @SerialName("mirror")
    override val mirror: String,
    @SerialName("args")
    val args: SubmitTx,
    @kotlinx.serialization.Transient
    val completableDeferred: CompletableDeferred<MsgSubmitTxResponse> = CompletableDeferred(),
) : JsonWspRequest()

@Serializable
data class SubmitTx(
    @SerialName("submit")
    val submit: String
)
// JSON Example
// {
//    "type": "jsonwsp/request",
//    "version": "1.0",
//    "servicename": "ogmios",
//    "methodname": "SubmitTx",
//    "args": {
//      "submit": "1092304a09875d008..."
//    }
// }
