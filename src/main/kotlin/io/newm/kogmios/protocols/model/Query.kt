package io.projectnewm.kogmios.protocols.model

import io.projectnewm.kogmios.protocols.model.serializers.QuerySerializer
import kotlinx.serialization.Serializable

@Serializable(with = QuerySerializer::class)
abstract class Query
