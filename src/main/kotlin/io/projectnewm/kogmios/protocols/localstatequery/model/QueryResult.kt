package io.projectnewm.kogmios.protocols.localstatequery.model

import io.projectnewm.kogmios.protocols.localstatequery.serializers.QueryResultSerializer
import kotlinx.serialization.Serializable

@Serializable(with = QueryResultSerializer::class)
interface QueryResult
