package io.newm.kogmios.protocols.messages

import com.fasterxml.jackson.annotation.JsonProperty

import com.fasterxml.jackson.annotation.JsonTypeName

import io.newm.kogmios.protocols.model.result.AcquireResult

/**
 * Response that comes back from Ogmios after an acquire message is sent.
 */

@JsonTypeName(MsgAcquire.METHOD_NAME)
data class MsgAcquireResponse(
    @param:JsonProperty(value = "result", required = true)
    @get:JsonProperty("result")
    override val result: AcquireResult,
    @param:JsonProperty(value = "id", required = true)
    @get:JsonProperty("id")
    override val id: String,
) : JsonRpcSuccessResponse()
