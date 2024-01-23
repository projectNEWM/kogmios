package io.newm.kogmios.protocols.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EraParameters(
    @SerialName("epochLength")
    val epochLength: Long,
    @SerialName("slotLength")
    val slotLength: Milliseconds,
    @SerialName("safeZone")
    val safeZone: Long? = null,
)
