package io.newm.kogmios.protocols.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.apache.commons.numbers.fraction.BigFraction

@Serializable
data class ConstitutionalCommitteeVotingThresholds(
    @SerialName("default")
    @Contextual
    val default: BigFraction,
    @SerialName("stateOfNoConfidence")
    @Contextual
    val stateOfNoConfidence: BigFraction,
)
