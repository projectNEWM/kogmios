package io.projectnewm.kogmios.protocols.model

import io.projectnewm.kogmios.protocols.model.serializers.PointDetailOrOriginSerializer
import kotlinx.serialization.Serializable

@Serializable(with = PointDetailOrOriginSerializer::class)
abstract class PointDetailOrOrigin
