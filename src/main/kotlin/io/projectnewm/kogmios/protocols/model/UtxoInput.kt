package io.projectnewm.kogmios.protocols.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UtxoInput(
    @SerialName("txId")
    val txId: String,

    @SerialName("index")
    val index: Long,
)
