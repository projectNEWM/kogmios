package io.projectnewm.kogmios.protocols.model

import io.projectnewm.kogmios.protocols.model.serializers.QueryNonMyopicMemberWardsResultSerializer
import kotlinx.serialization.Serializable

@Serializable(with = QueryNonMyopicMemberWardsResultSerializer::class)
class QueryNonMyopicMemberRewardsResult : LinkedHashMap<String, NonMyopicMemberRewardsResult>(), QueryResult
