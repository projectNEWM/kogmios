package io.projectnewm.kogmios.protocols.model

import io.projectnewm.kogmios.protocols.model.serializers.QueryStakeDistributionResultSerializer
import kotlinx.serialization.Serializable

@Serializable(with = QueryStakeDistributionResultSerializer::class)
class QueryStakeDistributionResult : LinkedHashMap<String, PoolDistribution>(), QueryResult
