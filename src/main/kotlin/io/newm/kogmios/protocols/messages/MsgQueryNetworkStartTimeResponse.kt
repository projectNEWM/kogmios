package io.newm.kogmios.protocols.messages

import io.newm.kogmios.protocols.model.result.InstantResult
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Response that comes back from Ogmios after a queryNetworkStartTime message is sent.
 */
@Serializable
@SerialName(MsgQuery.METHOD_QUERY_NETWORK_START_TIME)
data class MsgQueryNetworkStartTimeResponse(
    @SerialName("result")
    override val result: InstantResult,
    @SerialName("id")
    override val id: String,
) : JsonRpcSuccessResponse(result)
