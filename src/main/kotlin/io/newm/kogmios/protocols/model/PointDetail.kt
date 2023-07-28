package io.newm.kogmios.protocols.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PointDetail(
    @SerialName("slot")
    val slot: Long,
    @SerialName("id")
    val id: String,
) : PointDetailOrOrigin()
