package io.newm.kogmios.protocols.model.result

import com.fasterxml.jackson.annotation.JsonTypeName

import io.newm.kogmios.protocols.messages.MsgNextTransaction
import io.newm.kogmios.protocols.model.Tx

@JsonTypeName(MsgNextTransaction.METHOD_NAME)
data class TxResult(
    val transaction: Tx? = null,
) : OgmiosResult
