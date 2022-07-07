package io.projectnewm.kogmios.protocols.model

import io.projectnewm.kogmios.protocols.model.serializers.QueryPoolParametersResultSerializer
import kotlinx.serialization.Serializable

@Serializable(with = QueryPoolParametersResultSerializer::class)
class QueryPoolParametersResult : LinkedHashMap<String, PoolResult>(), QueryResult
