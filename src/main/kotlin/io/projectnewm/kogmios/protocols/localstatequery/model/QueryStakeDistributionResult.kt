package io.projectnewm.kogmios.protocols.localstatequery.model

import io.projectnewm.kogmios.protocols.localstatequery.serializers.QueryStakeDistributionResultSerializer
import kotlinx.serialization.Serializable

@Serializable(with = QueryStakeDistributionResultSerializer::class)
class QueryStakeDistributionResult : LinkedHashMap<String, PoolDistribution>(), QueryResult
