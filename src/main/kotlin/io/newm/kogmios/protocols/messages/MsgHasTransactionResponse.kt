package io.newm.kogmios.protocols.messages

import io.newm.kogmios.protocols.model.result.BooleanResult
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Response that comes back from Ogmios after a hasTransaction mempool message is sent.
 */
@Serializable
@SerialName(MsgHasTransaction.METHOD_NAME)
data class MsgHasTransactionResponse(
    @SerialName("result")
    override val result: BooleanResult,
    @SerialName("id")
    override val id: String,
) : JsonRpcSuccessResponse()
