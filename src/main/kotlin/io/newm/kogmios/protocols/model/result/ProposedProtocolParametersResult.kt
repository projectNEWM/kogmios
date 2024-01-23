package io.newm.kogmios.protocols.model.result

import io.newm.kogmios.protocols.model.ProposedProtocolParameters
import io.newm.kogmios.protocols.model.serializers.ProposedProtocolParametersResultSerializer
import kotlinx.serialization.Serializable

@Serializable(with = ProposedProtocolParametersResultSerializer::class)
class ProposedProtocolParametersResult : ArrayList<ProposedProtocolParameters>(), OgmiosResult {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ProposedProtocolParametersResult) return false
        if (!super.equals(other)) return false
        return true
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }
}
