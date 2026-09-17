package io.newm.kogmios.protocols.model

import com.fasterxml.jackson.annotation.JsonProperty

import org.apache.commons.numbers.fraction.BigFraction

data class PoolDistribution(
    @param:JsonProperty(value = "stake", required = true)
    @get:JsonProperty("stake")
    val stake: BigFraction,
    @param:JsonProperty(value = "vrf", required = true)
    @get:JsonProperty("vrf")
    val vrf: String,
)
