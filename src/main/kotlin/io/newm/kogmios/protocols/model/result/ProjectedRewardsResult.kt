package io.newm.kogmios.protocols.model.result

import io.newm.kogmios.protocols.model.NonMyopicMemberRewardsResult
import io.newm.kogmios.protocols.model.serializers.ProjectedRewardsResultSerializer
import kotlinx.serialization.Serializable

@Serializable(with = ProjectedRewardsResultSerializer::class)
class ProjectedRewardsResult : LinkedHashMap<String, NonMyopicMemberRewardsResult>(), OgmiosResult {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ProjectedRewardsResult) return false
        if (!super.equals(other)) return false
        return true
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }
}
