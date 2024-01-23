package io.newm.kogmios.protocols.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StakePool(
    @SerialName("id")
    val id: String,
)
