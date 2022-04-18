package io.projectnewm.kogmios.protocols.localstatequery.model

import io.projectnewm.kogmios.protocols.localstatequery.serializers.QueryDelegationsAndRewardsResultSerializer

@kotlinx.serialization.Serializable(with = QueryDelegationsAndRewardsResultSerializer::class)
class QueryDelegationsAndRewardsResult : LinkedHashMap<String, DelegationsAndRewardsResult>(), QueryResult
