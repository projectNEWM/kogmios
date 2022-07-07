package io.newm.kogmios.protocols.model

import io.newm.kogmios.protocols.model.serializers.QueryPoolParametersResultSerializer
import kotlinx.serialization.Serializable

@Serializable(with = QueryPoolParametersResultSerializer::class)
class QueryPoolParametersResult : LinkedHashMap<String, PoolResult>(), QueryResult
