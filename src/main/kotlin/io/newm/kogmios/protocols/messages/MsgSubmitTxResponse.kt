package io.newm.kogmios.protocols.messages

import io.newm.kogmios.protocols.model.result.SubmitTxResult
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Response that comes back from Ogmios after a submitTx message is sent.
 */
@Serializable
@SerialName(MsgSubmitTx.METHOD_SUBMIT_TX)
data class MsgSubmitTxResponse(
    @SerialName("result")
    override val result: SubmitTxResult,
    @SerialName("id")
    override val id: String,
) : JsonRpcSuccessResponse()
