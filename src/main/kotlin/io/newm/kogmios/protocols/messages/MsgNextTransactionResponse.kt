package io.newm.kogmios.protocols.messages

import io.newm.kogmios.protocols.model.result.TxResult
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Response that comes back from Ogmios after a nextTransaction mempool message is sent.
 */
@Serializable
@SerialName(MsgNextTransaction.METHOD_NAME)
data class MsgNextTransactionResponse(
    @SerialName("result")
    override val result: TxResult,
    @SerialName("id")
    override val id: String,
) : JsonRpcSuccessResponse()
