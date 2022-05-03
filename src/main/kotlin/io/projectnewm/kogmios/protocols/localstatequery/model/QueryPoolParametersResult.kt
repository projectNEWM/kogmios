package io.projectnewm.kogmios.protocols.localstatequery.model

import io.projectnewm.kogmios.protocols.localstatequery.serializers.QueryPoolParametersResultSerializer
import kotlinx.serialization.Serializable

@Serializable(with = QueryPoolParametersResultSerializer::class)
class QueryPoolParametersResult : LinkedHashMap<String, PoolResult>(), QueryResult
