package io.newm.kogmios.protocols.model

import io.newm.kogmios.protocols.Const.ORIGIN
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Origin(
    @SerialName("point")
    val point: String = ORIGIN,
) : PointOrOrigin()
