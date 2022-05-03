package io.projectnewm.kogmios.protocols.localstatequery.model

import io.projectnewm.kogmios.protocols.localstatequery.serializers.EraSummariesQueryResultSerializer
import kotlinx.serialization.Serializable

@Serializable(with = EraSummariesQueryResultSerializer::class)
data class EraSummariesQueryResult(val value: List<EraSummary>) : QueryResult
