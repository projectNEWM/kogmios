package io.newm.kogmios.protocols.messages

import io.newm.kogmios.protocols.model.result.SizeOfMempoolResult
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Response that comes back from Ogmios after a sizeOfMempool message is sent.
 */
@Serializable
@SerialName(MsgSizeOfMempool.METHOD_NAME)
data class MsgSizeOfMempoolResponse(
    @SerialName("result")
    override val result: SizeOfMempoolResult,
    @SerialName("id")
    override val id: String,
) : JsonRpcSuccessResponse()
