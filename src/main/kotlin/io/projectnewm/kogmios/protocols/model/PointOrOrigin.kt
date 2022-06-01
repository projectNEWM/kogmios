package io.projectnewm.kogmios.protocols.model

import io.projectnewm.kogmios.protocols.model.serializers.PointOrOriginSerializer
import kotlinx.serialization.Serializable

@Serializable(with = PointOrOriginSerializer::class)
abstract class PointOrOrigin
