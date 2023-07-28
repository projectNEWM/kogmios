package io.newm.kogmios.protocols.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UtxoOutputReference(
    @SerialName("transaction")
    val transaction: Transaction,
    @SerialName("index")
    val index: Long,
)
