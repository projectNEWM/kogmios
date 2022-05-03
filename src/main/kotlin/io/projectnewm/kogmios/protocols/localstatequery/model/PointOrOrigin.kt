package io.projectnewm.kogmios.protocols.localstatequery.model

import io.projectnewm.kogmios.protocols.localstatequery.serializers.PointOrOriginSerializer
import kotlinx.serialization.Serializable

@Serializable(with = PointOrOriginSerializer::class)
abstract class PointOrOrigin
