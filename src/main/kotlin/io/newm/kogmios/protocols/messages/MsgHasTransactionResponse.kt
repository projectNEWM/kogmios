package io.newm.kogmios.protocols.messages

import com.fasterxml.jackson.annotation.JsonProperty

import com.fasterxml.jackson.annotation.JsonTypeName

import io.newm.kogmios.protocols.model.result.BooleanResult

/**
 * Response that comes back from Ogmios after a hasTransaction mempool message is sent.
 */

@JsonTypeName(MsgHasTransaction.METHOD_NAME)
data class MsgHasTransactionResponse(
    @param:JsonProperty(value = "result", required = true)
    @get:JsonProperty("result")
    override val result: BooleanResult,
    @param:JsonProperty(value = "id", required = true)
    @get:JsonProperty("id")
    override val id: String,
) : JsonRpcSuccessResponse()
