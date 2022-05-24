package io.projectnewm.kogmios.protocols.localstatequery.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FindIntersect(
    @SerialName("points")
    val points: List<PointDetailOrOrigin>
)
