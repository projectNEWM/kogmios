package io.newm.kogmios.protocols.messages

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.UUID

/**
 * Request the Next transaction from the mempool snapshot
 */
@Serializable
data class MsgNextTransaction(
    @SerialName("method")
    override val method: String = METHOD_NAME,
    @SerialName("params")
    val params: Fields = Fields(),
    @SerialName("id")
    override val id: String = "$method: ${UUID.randomUUID()}",
) : JsonRpcRequest() {
    companion object {
        const val METHOD_NAME = "nextTransaction"
    }
}

@Serializable
data class Fields(
    @SerialName("fields")
    val fields: String = "all",
)
