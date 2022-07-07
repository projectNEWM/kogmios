package io.projectnewm.kogmios.protocols.model

import io.projectnewm.kogmios.protocols.model.serializers.QueryDelegationsAndRewardsResultSerializer
import kotlinx.serialization.Serializable

@Serializable(with = QueryDelegationsAndRewardsResultSerializer::class)
class QueryDelegationsAndRewardsResult : LinkedHashMap<String, DelegationsAndRewardsResult>(), QueryResult
