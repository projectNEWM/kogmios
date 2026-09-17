package io.newm.kogmios.protocols.model

import com.fasterxml.jackson.annotation.JsonProperty

data class ParamsProjectedRewards(
    @param:JsonProperty(value = "stake", required = true)
    @get:JsonProperty("stake")
    val stake: List<AdaRewardsInput>,
    @param:JsonProperty(value = "scripts", required = true)
    @get:JsonProperty("scripts")
    val scripts: List<String>,
    @param:JsonProperty(value = "keys", required = true)
    @get:JsonProperty("keys")
    val keys: List<String>,
) : Params()
