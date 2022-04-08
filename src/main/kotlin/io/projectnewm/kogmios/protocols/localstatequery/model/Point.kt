package io.projectnewm.kogmios.protocols.localstatequery.model

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class Point(
    @SerialName("point")
    val point: PointDetail
) : PointOrOrigin()
