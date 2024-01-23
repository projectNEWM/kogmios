package io.newm.kogmios.protocols.messages

import io.newm.kogmios.protocols.model.FindIntersect
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.UUID

/**
 * Acquire a point of intersection for syncing the blockchain
 */
@Serializable
data class MsgFindIntersect(
    @SerialName("method")
    override val method: String = METHOD_FIND_INTERSECTION,
    @SerialName("params")
    val params: FindIntersect,
    @SerialName("id")
    override val id: String = "$method: ${UUID.randomUUID()}",
) : JsonRpcRequest() {
    companion object {
        const val METHOD_FIND_INTERSECTION = "findIntersection"
    }
}
