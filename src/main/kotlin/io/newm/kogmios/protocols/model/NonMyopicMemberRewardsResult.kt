package io.projectnewm.kogmios.protocols.model

import io.projectnewm.kogmios.protocols.model.serializers.NonMyopicMemberRewardsResultSerializer
import kotlinx.serialization.Serializable

@Serializable(with = NonMyopicMemberRewardsResultSerializer::class)
class NonMyopicMemberRewardsResult : LinkedHashMap<String, Long>()
