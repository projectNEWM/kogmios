package io.newm.kogmios.protocols.model.result

import io.newm.kogmios.protocols.model.RewardAccountSummary
import io.newm.kogmios.protocols.model.serializers.RewardAccountSummariesResultSerializer
import kotlinx.serialization.Serializable

@Serializable(with = RewardAccountSummariesResultSerializer::class)
class RewardAccountSummariesResult : LinkedHashMap<String, RewardAccountSummary>(), OgmiosResult {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is RewardAccountSummariesResult) return false
        if (!super.equals(other)) return false
        return true
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }
}
