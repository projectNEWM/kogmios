package io.projectnewm.kogmios.protocols.localstatequery.model

import io.projectnewm.kogmios.protocols.localstatequery.serializers.QueryNonMyopicMemberWardsResultSerializer
import kotlinx.serialization.Serializable

@Serializable(with = QueryNonMyopicMemberWardsResultSerializer::class)
class QueryNonMyopicMemberRewardsResult : LinkedHashMap<String, NonMyopicMemberRewardsResult>(), QueryResult
