package io.newm.kogmios.protocols.model

import io.newm.kogmios.protocols.model.serializers.QuerySerializer
import kotlinx.serialization.Serializable

@Serializable(with = QuerySerializer::class)
abstract class Query
