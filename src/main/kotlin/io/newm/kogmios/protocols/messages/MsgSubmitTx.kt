package io.newm.kogmios.protocols.messages

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.UUID

/**
 * Submit a transaction to the node's mempool.
 */
@Serializable
data class MsgSubmitTx(
    @SerialName("method")
    override val method: String = METHOD_SUBMIT_TX,
    @SerialName("params")
    val params: SubmitOrEvalTx,
    @SerialName("id")
    override val id: String = "$method: ${UUID.randomUUID()}",
) : JsonRpcRequest() {
    companion object {
        const val METHOD_SUBMIT_TX = "submitTransaction"
    }
}

@Serializable
data class SubmitOrEvalTx(
    @SerialName("transaction")
    val transaction: Cbor,
)

@Serializable
data class Cbor(
    @SerialName("cbor")
    val cbor: String,
)
