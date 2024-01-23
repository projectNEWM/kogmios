package io.newm.kogmios.protocols.model.result

import io.newm.kogmios.protocols.model.PoolResult
import io.newm.kogmios.protocols.model.serializers.StakePoolsResultSerializer
import kotlinx.serialization.Serializable

@Serializable(with = StakePoolsResultSerializer::class)
class StakePoolsResult : LinkedHashMap<String, PoolResult>(), OgmiosResult {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is StakePoolsResult) return false
        return super.equals(other)
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }
}
