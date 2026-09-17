package io.newm.kogmios.protocols.model

import com.fasterxml.jackson.annotation.JsonProperty

import io.newm.kogmios.protocols.model.result.StakePoolsResult

data class ShelleyGenesisStakePools(
    @param:JsonProperty(value = "stakePools", required = true)
    @get:JsonProperty("stakePools")
    val stakePools: StakePoolsResult,
    @param:JsonProperty(value = "delegators", required = true)
    @get:JsonProperty("delegators")
    val delegators: Map<String, String>,
)
