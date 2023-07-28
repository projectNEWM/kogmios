package io.newm.kogmios.protocols.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.apache.commons.numbers.fraction.BigFraction

@Serializable
data class ConwayConstitutionalCommittee(
    @SerialName("members")
    val members: List<ConwayConstitutionalCommitteeMember>,
    @Contextual
    @SerialName("quorum")
    val quorum: BigFraction,
)
