package io.projectnewm.kogmios.protocols.localstatequery.model

import io.projectnewm.kogmios.protocols.localstatequery.serializers.StringArrayQueryResultSerializer
import kotlinx.serialization.Serializable

@Serializable(with = StringArrayQueryResultSerializer::class)
class StringArrayQueryResult(val value: List<String>) : QueryResult
