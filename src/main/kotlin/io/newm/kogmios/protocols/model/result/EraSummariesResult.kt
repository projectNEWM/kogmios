package io.newm.kogmios.protocols.model.result

import io.newm.kogmios.protocols.model.EraSummary
import io.newm.kogmios.protocols.model.serializers.EraSummariesResultSerializer
import kotlinx.serialization.Serializable

@Serializable(with = EraSummariesResultSerializer::class)
class EraSummariesResult :
    ArrayList<EraSummary>(),
    OgmiosResult {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is EraSummariesResult) return false
        if (!super.equals(other)) return false
        return true
    }

    override fun hashCode(): Int = super.hashCode()
}
