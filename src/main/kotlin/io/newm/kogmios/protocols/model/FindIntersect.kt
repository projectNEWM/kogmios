package io.newm.kogmios.protocols.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FindIntersect(
    @SerialName("points")
    val points: List<PointDetailOrOrigin>,
)
