package io.newm.kogmios.protocols.messages

import io.newm.kogmios.protocols.model.result.LongResult
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Response that comes back from Ogmios after a queryNetworkBlockHeight message is sent.
 */
@Serializable
@SerialName(MsgQuery.METHOD_QUERY_NETWORK_BLOCK_HEIGHT)
data class MsgQueryBlockHeightResponse(
    @SerialName("result")
    override val result: LongResult,
    @SerialName("id")
    override val id: String,
) : JsonRpcSuccessResponse(result)
