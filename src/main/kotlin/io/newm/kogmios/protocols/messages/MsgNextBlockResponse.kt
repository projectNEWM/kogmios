package io.newm.kogmios.protocols.messages

import com.fasterxml.jackson.annotation.JsonProperty

import com.fasterxml.jackson.annotation.JsonTypeName

import io.newm.kogmios.protocols.model.result.NextBlockResult

/**
 * Response that comes back from Ogmios after a nextBlock message is sent.
 */

@JsonTypeName(MsgNextBlock.METHOD_NEXT_BLOCK)
data class MsgNextBlockResponse(
    @param:JsonProperty(value = "result", required = true)
    @get:JsonProperty("result")
    override val result: NextBlockResult,
    @param:JsonProperty(value = "id", required = true)
    @get:JsonProperty("id")
    override val id: String,
) : JsonRpcSuccessResponse()
