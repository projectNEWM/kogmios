package io.newm.kogmios.protocols.messages

import io.newm.kogmios.protocols.model.result.AcquireResult
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Response that comes back from Ogmios after an acquire message is sent.
 */
@Serializable
@SerialName(MsgAcquire.METHOD_NAME)
data class MsgAcquireResponse(
    @SerialName("result")
    override val result: AcquireResult,
    @SerialName("id")
    override val id: String,
) : JsonRpcSuccessResponse(result)
