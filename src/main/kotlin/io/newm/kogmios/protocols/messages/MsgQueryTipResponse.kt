package io.newm.kogmios.protocols.messages

import com.fasterxml.jackson.annotation.JsonProperty

import com.fasterxml.jackson.annotation.JsonTypeName

import io.newm.kogmios.protocols.model.result.TipResult

/**
 * Response that comes back from Ogmios after a queryNetworkTip message is sent.
 */

@JsonTypeName(MsgQuery.METHOD_QUERY_NETWORK_TIP)
data class MsgQueryTipResponse(
    @param:JsonProperty(value = "result", required = true)
    @get:JsonProperty("result")
    override val result: TipResult,
    @param:JsonProperty(value = "id", required = true)
    @get:JsonProperty("id")
    override val id: String,
) : JsonRpcSuccessResponse()
