package io.newm.kogmios.protocols.model

import io.newm.kogmios.protocols.model.result.StakePoolsResult
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ShelleyGenesisStakePools(
    @SerialName("stakePools")
    val stakePools: StakePoolsResult,
    @SerialName("delegators")
    val delegators: Map<String, String>,
)
