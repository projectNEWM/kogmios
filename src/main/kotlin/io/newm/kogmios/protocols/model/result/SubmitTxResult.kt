package io.newm.kogmios.protocols.model.result

import io.newm.kogmios.protocols.model.Transaction
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SubmitTxResult(
    @SerialName("transaction")
    val transaction: Transaction,
) : OgmiosResult
