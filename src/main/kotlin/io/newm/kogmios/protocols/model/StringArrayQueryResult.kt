package io.newm.kogmios.protocols.model

import io.newm.kogmios.protocols.model.serializers.StringArrayQueryResultSerializer
import kotlinx.serialization.Serializable

@Serializable(with = StringArrayQueryResultSerializer::class)
class StringArrayQueryResult(val value: List<String>) : QueryResult
