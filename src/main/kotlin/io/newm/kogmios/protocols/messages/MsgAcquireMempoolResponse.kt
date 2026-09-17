package io.newm.kogmios.protocols.messages

import com.fasterxml.jackson.annotation.JsonProperty

import com.fasterxml.jackson.annotation.JsonTypeName

import io.newm.kogmios.protocols.model.result.AcquireMempoolResult

/**
 * Response that comes back from Ogmios after an AwaitAcquire message is sent.
 */

@JsonTypeName(MsgAcquireMempool.METHOD_NAME)
data class MsgAcquireMempoolResponse(
    @param:JsonProperty(value = "result", required = true)
    @get:JsonProperty("result")
    override val result: AcquireMempoolResult,
    @param:JsonProperty(value = "id", required = true)
    @get:JsonProperty("id")
    override val id: String,
) : JsonRpcSuccessResponse()
