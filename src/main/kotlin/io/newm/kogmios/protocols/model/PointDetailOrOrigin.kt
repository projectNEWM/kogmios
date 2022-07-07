package io.newm.kogmios.protocols.model

import io.newm.kogmios.protocols.model.serializers.PointDetailOrOriginSerializer
import kotlinx.serialization.Serializable

@Serializable(with = PointDetailOrOriginSerializer::class)
abstract class PointDetailOrOrigin
