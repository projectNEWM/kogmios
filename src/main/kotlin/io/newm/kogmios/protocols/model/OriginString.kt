package io.newm.kogmios.protocols.model

import io.newm.kogmios.protocols.Const
import io.newm.kogmios.protocols.model.serializers.OriginStringSerializer
import kotlinx.serialization.Serializable

@Serializable(with = OriginStringSerializer::class)
data class OriginString(
    val origin: String = Const.ORIGIN
) : PointDetailOrOrigin()