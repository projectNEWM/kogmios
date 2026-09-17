package io.newm.kogmios.protocols.messages

import com.fasterxml.jackson.annotation.JsonProperty

import java.util.UUID

/**
 * Check whether the mempool snapshot has a given transaction in it.
 */

data class MsgHasTransaction(
    override val method: String = METHOD_NAME,
    @param:JsonProperty(value = "params", required = true)
    @get:JsonProperty("params")
    val params: HasTransaction,
    override val id: String = "$method: ${UUID.randomUUID()}",
) : JsonRpcRequest() {
    companion object {
        const val METHOD_NAME = "hasTransaction"
    }
}

data class HasTransaction(
    @param:JsonProperty(value = "id", required = true)
    @get:JsonProperty("id")
    val id: String,
)
