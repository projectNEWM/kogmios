package io.newm.kogmios.protocols.messages

import com.fasterxml.jackson.annotation.JsonProperty

import com.fasterxml.jackson.annotation.JsonTypeName

import io.newm.kogmios.protocols.model.result.TxResult

/**
 * Response that comes back from Ogmios after a nextTransaction mempool message is sent.
 */

@JsonTypeName(MsgNextTransaction.METHOD_NAME)
data class MsgNextTransactionResponse(
    @param:JsonProperty(value = "result", required = true)
    @get:JsonProperty("result")
    override val result: TxResult,
    @param:JsonProperty(value = "id", required = true)
    @get:JsonProperty("id")
    override val id: String,
) : JsonRpcSuccessResponse()
