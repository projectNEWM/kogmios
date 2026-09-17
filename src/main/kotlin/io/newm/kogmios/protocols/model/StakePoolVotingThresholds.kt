package io.newm.kogmios.protocols.model

import com.fasterxml.jackson.annotation.JsonProperty

import org.apache.commons.numbers.fraction.BigFraction

data class StakePoolVotingThresholds(
    @param:JsonProperty(value = "noConfidence", required = true)
    @get:JsonProperty("noConfidence")
    val noConfidence: BigFraction,
    @param:JsonProperty(value = "constitutionalCommittee", required = true)
    @get:JsonProperty("constitutionalCommittee")
    val constitutionalCommittee: ConstitutionalCommitteeVotingThresholds,
    @param:JsonProperty(value = "hardForkInitiation", required = true)
    @get:JsonProperty("hardForkInitiation")
    val hardForkInitiation: BigFraction,
    @param:JsonProperty(value = "protocolParametersUpdate", required = true)
    @get:JsonProperty("protocolParametersUpdate")
    val protocolParametersUpdate: StakePoolProtocolParametersUpdateThresholds,
)
