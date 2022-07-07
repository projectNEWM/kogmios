package io.projectnewm.kogmios.protocols.model

import io.projectnewm.kogmios.protocols.model.serializers.QueryResultSerializer
import kotlinx.serialization.Serializable

@Serializable(with = QueryResultSerializer::class)
interface QueryResult
