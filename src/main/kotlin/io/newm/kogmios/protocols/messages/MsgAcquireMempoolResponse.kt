package io.newm.kogmios.protocols.messages

import io.newm.kogmios.protocols.model.result.AcquireMempoolResult
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Response that comes back from Ogmios after an AwaitAcquire message is sent.
 */
@Serializable
@SerialName(MsgAcquireMempool.METHOD_NAME)
data class MsgAcquireMempoolResponse(
    @SerialName("result")
    override val result: AcquireMempoolResult,
    @SerialName("id")
    override val id: String,
) : JsonRpcSuccessResponse()
