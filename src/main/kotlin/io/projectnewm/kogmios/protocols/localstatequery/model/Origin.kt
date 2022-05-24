package io.projectnewm.kogmios.protocols.localstatequery.model

import io.projectnewm.kogmios.protocols.Const.ORIGIN
import io.projectnewm.kogmios.protocols.localstatequery.serializers.OriginStringSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Origin(
    @SerialName("point")
    val point: String = ORIGIN
) : PointOrOrigin()

@Serializable(with = OriginStringSerializer::class)
data class OriginString(
    val origin: String = ORIGIN
) : PointDetailOrOrigin()
