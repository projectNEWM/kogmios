package io.newm.kogmios.protocols.model

import com.fasterxml.jackson.annotation.JsonProperty

data class RewardAccountSummary(
    @param:JsonProperty(value = "delegate", required = true)
    @get:JsonProperty("delegate")
    val delegate: StakePool,
    @param:JsonProperty(value = "rewards", required = true)
    @get:JsonProperty("rewards")
    val rewards: Ada,
)
