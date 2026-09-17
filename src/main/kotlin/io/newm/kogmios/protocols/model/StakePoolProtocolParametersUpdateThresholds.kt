package io.newm.kogmios.protocols.model

import com.fasterxml.jackson.annotation.JsonProperty

import org.apache.commons.numbers.fraction.BigFraction

data class StakePoolProtocolParametersUpdateThresholds(
    @param:JsonProperty(value = "security", required = true)
    @get:JsonProperty("security")
    val security: BigFraction,
)
