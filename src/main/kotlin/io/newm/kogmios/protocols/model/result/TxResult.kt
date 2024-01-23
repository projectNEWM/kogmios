package io.newm.kogmios.protocols.model.result

import io.newm.kogmios.protocols.messages.MsgNextTransaction
import io.newm.kogmios.protocols.model.Tx
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(MsgNextTransaction.METHOD_NAME)
data class TxResult(
    @SerialName("transaction")
    val transaction: Tx? = null,
) : OgmiosResult
