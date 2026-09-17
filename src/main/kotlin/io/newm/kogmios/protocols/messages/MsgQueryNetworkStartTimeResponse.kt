package io.newm.kogmios.protocols.messages

import com.fasterxml.jackson.annotation.JsonProperty

import com.fasterxml.jackson.annotation.JsonTypeName

import io.newm.kogmios.protocols.model.result.InstantResult

/**
 * Response that comes back from Ogmios after a queryNetworkStartTime message is sent.
 */

@JsonTypeName(MsgQuery.METHOD_QUERY_NETWORK_START_TIME)
data class MsgQueryNetworkStartTimeResponse(
    @param:JsonProperty(value = "result", required = true)
    @get:JsonProperty("result")
    override val result: InstantResult,
    @param:JsonProperty(value = "id", required = true)
    @get:JsonProperty("id")
    override val id: String,
) : JsonRpcSuccessResponse()
