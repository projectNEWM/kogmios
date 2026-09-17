package io.newm.kogmios.protocols.model

import com.fasterxml.jackson.annotation.JsonProperty

import org.apache.commons.numbers.fraction.BigFraction

data class ConwayConstitutionalCommittee(
    @param:JsonProperty(value = "members", required = true)
    @get:JsonProperty("members")
    val members: List<ConwayConstitutionalCommitteeMember>,
    @param:JsonProperty(value = "quorum", required = true)
    @get:JsonProperty("quorum")
    val quorum: BigFraction,
)
