package io.newm.kogmios.protocols.model.result

import io.newm.kogmios.protocols.model.PoolResult

class StakePoolsResult :
    LinkedHashMap<String, PoolResult>(),
    OgmiosResult {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is StakePoolsResult) return false
        return super.equals(other)
    }

    override fun hashCode(): Int = super.hashCode()
}
