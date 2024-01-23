package io.newm.kogmios.protocols.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RedeemerPointer(
    @SerialName("purpose")
    val purpose: String,
    @SerialName("index")
    val index: Int,
)
