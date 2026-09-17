package io.newm.kogmios.protocols.model

import com.fasterxml.jackson.annotation.JsonProperty

import org.apache.commons.numbers.fraction.BigFraction

data class DelegateRepresentativeVotingThresholds(
    @param:JsonProperty(value = "noConfidence", required = true)
    @get:JsonProperty("noConfidence")
    val noConfidence: BigFraction,
    @param:JsonProperty(value = "constitution", required = true)
    @get:JsonProperty("constitution")
    val constitution: BigFraction,
    @param:JsonProperty(value = "constitutionalCommittee", required = true)
    @get:JsonProperty("constitutionalCommittee")
    val constitutionalCommittee: ConstitutionalCommitteeVotingThresholds,
    @param:JsonProperty(value = "hardForkInitiation", required = true)
    @get:JsonProperty("hardForkInitiation")
    val hardForkInitiation: BigFraction,
    @param:JsonProperty(value = "protocolParametersUpdate", required = true)
    @get:JsonProperty("protocolParametersUpdate")
    val protocolParametersUpdate: ProtocolParametersUpdateThresholds,
    @param:JsonProperty(value = "treasuryWithdrawals", required = true)
    @get:JsonProperty("treasuryWithdrawals")
    val treasuryWithdrawals: BigFraction,
)
