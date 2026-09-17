package io.newm.kogmios.protocols.model

import com.fasterxml.jackson.annotation.JsonProperty

import org.apache.commons.numbers.fraction.BigFraction

data class ProtocolParametersUpdateThresholds(
    @param:JsonProperty(value = "network", required = true)
    @get:JsonProperty("network")
    val network: BigFraction,
    @param:JsonProperty(value = "economic", required = true)
    @get:JsonProperty("economic")
    val economic: BigFraction,
    @param:JsonProperty(value = "technical", required = true)
    @get:JsonProperty("technical")
    val technical: BigFraction,
    @param:JsonProperty(value = "governance", required = true)
    @get:JsonProperty("governance")
    val governance: BigFraction,
)
