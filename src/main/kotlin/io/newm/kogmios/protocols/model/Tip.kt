package io.newm.kogmios.protocols.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Tip(
    @SerialName("slot")
    val slot: Long,
    @SerialName("id")
    val id: String,
    @SerialName("height")
    val height: Long,
)
