package io.newm.kogmios.protocols.messages

import io.newm.kogmios.protocols.model.result.NextBlockResult
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Response that comes back from Ogmios after a nextBlock message is sent.
 */
@Serializable
@SerialName(MsgNextBlock.METHOD_NEXT_BLOCK)
data class MsgNextBlockResponse(
    @SerialName("result")
    override val result: NextBlockResult,
    @SerialName("id")
    override val id: String,
) : JsonRpcSuccessResponse()
