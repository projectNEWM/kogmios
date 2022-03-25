package io.projectnewm.kogmios.protocols.localstatequery.model

import io.projectnewm.kogmios.protocols.localstatequery.serializers.PointOrOriginSerializer

@kotlinx.serialization.Serializable(with = PointOrOriginSerializer::class)
abstract class PointOrOrigin