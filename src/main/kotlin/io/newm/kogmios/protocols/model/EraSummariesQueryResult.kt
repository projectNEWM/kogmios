package io.newm.kogmios.protocols.model

import io.newm.kogmios.protocols.model.serializers.EraSummariesQueryResultSerializer
import kotlinx.serialization.Serializable

@Serializable(with = EraSummariesQueryResultSerializer::class)
data class EraSummariesQueryResult(val value: List<EraSummary>) : QueryResult
