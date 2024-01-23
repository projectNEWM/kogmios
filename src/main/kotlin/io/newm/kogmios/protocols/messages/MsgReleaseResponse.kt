package io.newm.kogmios.protocols.messages

import io.newm.kogmios.protocols.model.result.ReleaseResult
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Response that comes back from Ogmios after a release message is sent.
 */
@Serializable
@SerialName(MsgRelease.METHOD_NAME)
data class MsgReleaseResponse(
    @SerialName("result")
    override val result: ReleaseResult,
    @SerialName("id")
    override val id: String,
) : JsonRpcSuccessResponse(result)
