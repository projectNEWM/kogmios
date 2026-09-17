package io.newm.kogmios.protocols.messages

import com.fasterxml.jackson.annotation.JsonProperty

import com.fasterxml.jackson.annotation.JsonTypeName

import io.newm.kogmios.protocols.model.result.SizeOfMempoolResult

/**
 * Response that comes back from Ogmios after a sizeOfMempool message is sent.
 */

@JsonTypeName(MsgSizeOfMempool.METHOD_NAME)
data class MsgSizeOfMempoolResponse(
    @param:JsonProperty(value = "result", required = true)
    @get:JsonProperty("result")
    override val result: SizeOfMempoolResult,
    @param:JsonProperty(value = "id", required = true)
    @get:JsonProperty("id")
    override val id: String,
) : JsonRpcSuccessResponse()
