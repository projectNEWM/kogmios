package io.newm.kogmios.protocols.messages

import com.fasterxml.jackson.annotation.JsonProperty

import io.newm.kogmios.protocols.model.FindIntersect

import java.util.UUID

/**
 * Acquire a point of intersection for syncing the blockchain
 */

data class MsgFindIntersect(
    override val method: String = METHOD_FIND_INTERSECTION,
    @param:JsonProperty(value = "params", required = true)
    @get:JsonProperty("params")
    val params: FindIntersect,
    override val id: String = "$method: ${UUID.randomUUID()}",
) : JsonRpcRequest() {
    companion object {
        const val METHOD_FIND_INTERSECTION = "findIntersection"
    }
}
