package io.newm.kogmios.protocols.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EraSummaryParameters(
    @SerialName("epochLength")
    val epochLength: Long,
    @SerialName("slotLength")
    val slotLength: Long,
    @SerialName("safeZone")
    val safeZone: Long,
)
