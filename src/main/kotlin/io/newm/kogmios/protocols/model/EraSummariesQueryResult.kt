package io.projectnewm.kogmios.protocols.model

import io.projectnewm.kogmios.protocols.model.serializers.EraSummariesQueryResultSerializer
import kotlinx.serialization.Serializable

@Serializable(with = EraSummariesQueryResultSerializer::class)
data class EraSummariesQueryResult(val value: List<EraSummary>) : QueryResult
