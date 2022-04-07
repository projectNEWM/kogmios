package io.projectnewm.kogmios.protocols.localstatequery.model

import io.projectnewm.kogmios.protocols.Const.ORIGIN
import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class Origin(
    @SerialName("point")
    val point: String = ORIGIN
) : PointOrOrigin()
