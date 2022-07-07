package io.projectnewm.kogmios.protocols.model

import io.projectnewm.kogmios.protocols.model.serializers.StringArrayQueryResultSerializer
import kotlinx.serialization.Serializable

@Serializable(with = StringArrayQueryResultSerializer::class)
class StringArrayQueryResult(val value: List<String>) : QueryResult
