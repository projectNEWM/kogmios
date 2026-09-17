package io.newm.kogmios.protocols.model

import com.fasterxml.jackson.annotation.JsonProperty

import org.apache.commons.numbers.fraction.BigFraction

data class ConstitutionalCommitteeVotingThresholds(
    @param:JsonProperty(value = "default", required = true)
    @get:JsonProperty("default")
    val default: BigFraction,
    @param:JsonProperty(value = "stateOfNoConfidence", required = true)
    @get:JsonProperty("stateOfNoConfidence")
    val stateOfNoConfidence: BigFraction,
)
