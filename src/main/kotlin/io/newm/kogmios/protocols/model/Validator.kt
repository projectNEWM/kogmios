package io.newm.kogmios.protocols.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Validator(
    @SerialName("index")
    val index: Int,
    @SerialName("purpose")
    val purpose: String,
)
