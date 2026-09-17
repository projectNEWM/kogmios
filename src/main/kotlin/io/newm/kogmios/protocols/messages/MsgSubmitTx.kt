package io.newm.kogmios.protocols.messages

import com.fasterxml.jackson.annotation.JsonProperty

import java.util.UUID

/**
 * Submit a transaction to the node's mempool.
 */

data class MsgSubmitTx(
    override val method: String = METHOD_SUBMIT_TX,
    @param:JsonProperty(value = "params", required = true)
    @get:JsonProperty("params")
    val params: SubmitOrEvalTx,
    override val id: String = "$method: ${UUID.randomUUID()}",
) : JsonRpcRequest() {
    companion object {
        const val METHOD_SUBMIT_TX = "submitTransaction"
    }
}

data class SubmitOrEvalTx(
    @param:JsonProperty(value = "transaction", required = true)
    @get:JsonProperty("transaction")
    val transaction: Cbor,
)

data class Cbor(
    @param:JsonProperty(value = "cbor", required = true)
    @get:JsonProperty("cbor")
    val cbor: String,
)
