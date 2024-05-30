package io.newm.kogmios.protocols.model.result

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LastKnownTip(
    @SerialName("height")
    val height: Long,
    @SerialName("id")
    val id: String,
    @SerialName("slot")
    val slot: Long,
)
