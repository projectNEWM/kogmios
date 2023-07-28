package io.newm.kogmios.protocols.messages

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.UUID

/**
 * Check whether the mempool snapshot has a given transaction in it.
 */
@Serializable
data class MsgHasTransaction(
    @SerialName("method")
    override val method: String = METHOD_NAME,
    @SerialName("params")
    val params: HasTransaction,
    @SerialName("id")
    override val id: String = "$method: ${UUID.randomUUID()}",
) : JsonRpcRequest() {
    companion object {
        const val METHOD_NAME = "hasTransaction"
    }
}

@Serializable
data class HasTransaction(
    @SerialName("id")
    val id: String,
)
