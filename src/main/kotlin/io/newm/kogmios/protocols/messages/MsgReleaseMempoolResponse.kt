package io.newm.kogmios.protocols.messages

import io.newm.kogmios.protocols.model.result.ReleaseMempoolResult
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Response that comes back from Ogmios after a release mempool message is sent.
 */
@Serializable
@SerialName(MsgReleaseMempool.METHOD_NAME)
data class MsgReleaseMempoolResponse(
    @SerialName("result")
    override val result: ReleaseMempoolResult,
    @SerialName("id")
    override val id: String,
) : JsonRpcSuccessResponse()
