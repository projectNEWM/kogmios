package io.newm.kogmios.protocols.model.result

import io.newm.kogmios.protocols.model.PoolDistribution
import io.newm.kogmios.protocols.model.serializers.LiveStakeDistributionResultSerializer
import kotlinx.serialization.Serializable

@Serializable(with = LiveStakeDistributionResultSerializer::class)
class LiveStakeDistributionResult :
    LinkedHashMap<String, PoolDistribution>(),
    OgmiosResult {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is LiveStakeDistributionResult) return false
        if (!super.equals(other)) return false
        return true
    }

    override fun hashCode(): Int = super.hashCode()
}
