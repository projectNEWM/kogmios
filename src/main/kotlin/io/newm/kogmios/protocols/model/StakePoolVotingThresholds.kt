package io.newm.kogmios.protocols.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.apache.commons.numbers.fraction.BigFraction

@Serializable
data class StakePoolVotingThresholds(
    @SerialName("noConfidence")
    @Contextual
    val noConfidence: BigFraction,
    @SerialName("constitutionalCommittee")
    val constitutionalCommittee: ConstitutionalCommitteeVotingThresholds,
    @SerialName("hardForkInitiation")
    @Contextual
    val hardForkInitiation: BigFraction,
    @SerialName("protocolParametersUpdate")
    val protocolParametersUpdate: StakePoolProtocolParametersUpdateThresholds,
)
