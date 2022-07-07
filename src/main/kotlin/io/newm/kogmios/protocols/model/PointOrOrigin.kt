package io.newm.kogmios.protocols.model

import io.newm.kogmios.protocols.model.serializers.PointOrOriginSerializer
import kotlinx.serialization.Serializable

@Serializable(with = PointOrOriginSerializer::class)
abstract class PointOrOrigin
