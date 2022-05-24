package io.projectnewm.kogmios.protocols.localstatequery.model

import io.projectnewm.kogmios.protocols.localstatequery.serializers.PointDetailOrOriginSerializer
import kotlinx.serialization.Serializable

@Serializable(with = PointDetailOrOriginSerializer::class)
abstract class PointDetailOrOrigin
