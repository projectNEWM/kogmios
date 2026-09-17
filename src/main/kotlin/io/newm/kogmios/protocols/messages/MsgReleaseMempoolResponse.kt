package io.newm.kogmios.protocols.messages

import com.fasterxml.jackson.annotation.JsonProperty

import com.fasterxml.jackson.annotation.JsonTypeName

import io.newm.kogmios.protocols.model.result.ReleaseMempoolResult

/**
 * Response that comes back from Ogmios after a release mempool message is sent.
 */

@JsonTypeName(MsgReleaseMempool.METHOD_NAME)
data class MsgReleaseMempoolResponse(
    @param:JsonProperty(value = "result", required = true)
    @get:JsonProperty("result")
    override val result: ReleaseMempoolResult,
    @param:JsonProperty(value = "id", required = true)
    @get:JsonProperty("id")
    override val id: String,
) : JsonRpcSuccessResponse()
