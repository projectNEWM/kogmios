package io.projectnewm.kogmios.protocols.localstatequery.model

import io.projectnewm.kogmios.protocols.localstatequery.serializers.NonMyopicMemberRewardsResultSerializer
import kotlinx.serialization.Serializable

@Serializable(with = NonMyopicMemberRewardsResultSerializer::class)
class NonMyopicMemberRewardsResult : LinkedHashMap<String, Long>()
