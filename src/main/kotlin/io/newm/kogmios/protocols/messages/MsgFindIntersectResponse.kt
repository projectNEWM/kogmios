package io.newm.kogmios.protocols.messages

import io.newm.kogmios.protocols.model.result.IntersectionFoundResult
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Response that comes back from Ogmios after a MsgFindIntersect message is sent.
 */
@Serializable
@SerialName(MsgFindIntersect.METHOD_FIND_INTERSECTION)
data class MsgFindIntersectResponse(
    @SerialName("result")
    override val result: IntersectionFoundResult,
    @SerialName("id")
    override val id: String,
) : JsonRpcSuccessResponse()
