package io.newm.kogmios.protocols.model

import io.newm.kogmios.protocols.model.serializers.QueryResultSerializer
import kotlinx.serialization.Serializable

@Serializable(with = QueryResultSerializer::class)
interface QueryResult
