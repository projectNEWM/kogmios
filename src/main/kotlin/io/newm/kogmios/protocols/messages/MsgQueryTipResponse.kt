package io.newm.kogmios.protocols.messages

import io.newm.kogmios.protocols.model.result.TipResult
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Response that comes back from Ogmios after a queryNetworkTip message is sent.
 */
@Serializable
@SerialName(MsgQuery.METHOD_QUERY_NETWORK_TIP)
data class MsgQueryTipResponse(
    @SerialName("result")
    override val result: TipResult,
    @SerialName("id")
    override val id: String,
) : JsonRpcSuccessResponse(result)
