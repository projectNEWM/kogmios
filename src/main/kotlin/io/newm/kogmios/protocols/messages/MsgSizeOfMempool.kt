package io.newm.kogmios.protocols.messages

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.UUID

/**
 * Check the mempool size and capacity values
 */
@Serializable
data class MsgSizeOfMempool(
    @SerialName("method")
    override val method: String = METHOD_NAME,
    @SerialName("id")
    override val id: String = "$method: ${UUID.randomUUID()}",
) : JsonRpcRequest() {
    companion object {
        const val METHOD_NAME = "sizeOfMempool"
    }
}
