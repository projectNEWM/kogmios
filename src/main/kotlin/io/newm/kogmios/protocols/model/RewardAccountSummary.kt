package io.newm.kogmios.protocols.model

import com.fasterxml.jackson.annotation.JsonProperty

data class RewardAccountSummary(
    @param:JsonProperty(value = "from", required = true)
    @get:JsonProperty("from")
    val from: String,
    @param:JsonProperty(value = "credential", required = true)
    @get:JsonProperty("credential")
    val credential: String,
    @param:JsonProperty(value = "rewards", required = true)
    @get:JsonProperty("rewards")
    val rewards: Ada,
    @param:JsonProperty(value = "deposit", required = true)
    @get:JsonProperty("deposit")
    val deposit: Ada,
    val stakePool: StakePool? = null,
    val delegateRepresentative: DelegateRepresentative? = null,
)
