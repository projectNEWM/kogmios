package io.newm.kogmios.protocols.messages

import com.fasterxml.jackson.annotation.JsonProperty

import com.fasterxml.jackson.annotation.JsonTypeName

import io.newm.kogmios.protocols.model.result.IntersectionFoundResult

/**
 * Response that comes back from Ogmios after a MsgFindIntersect message is sent.
 */

@JsonTypeName(MsgFindIntersect.METHOD_FIND_INTERSECTION)
data class MsgFindIntersectResponse(
    @param:JsonProperty(value = "result", required = true)
    @get:JsonProperty("result")
    override val result: IntersectionFoundResult,
    @param:JsonProperty(value = "id", required = true)
    @get:JsonProperty("id")
    override val id: String,
) : JsonRpcSuccessResponse()
