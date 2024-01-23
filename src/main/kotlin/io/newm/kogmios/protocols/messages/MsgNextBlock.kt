package io.newm.kogmios.protocols.messages

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.UUID

/**
 * Request the next block when syncing the blockchain.
 */
@Serializable
data class MsgNextBlock(
    @SerialName("method")
    override val method: String = METHOD_NEXT_BLOCK,
    @SerialName("id")
    override val id: String = "$method: ${UUID.randomUUID()}",
) : JsonRpcRequest() {
    companion object {
        const val METHOD_NEXT_BLOCK = "nextBlock"
    }
}
