package io.projectnewm.kogmios.protocols.localstatequery.model

import io.projectnewm.kogmios.protocols.localstatequery.serializers.QueryPoolParametersResultSerializer

@kotlinx.serialization.Serializable(with = QueryPoolParametersResultSerializer::class)
class QueryPoolParametersResult : LinkedHashMap<String, PoolResult>(), QueryResult
