package io.projectnewm.kogmios.protocols.localstatequery.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Point(
    @SerialName("point")
    val point: PointDetail
) : PointOrOrigin()
