package io.newm.kogmios.protocols.model

import com.fasterxml.jackson.annotation.JsonProperty

data class GovernanceVote(
    @param:JsonProperty(value = "issuer", required = true)
    @get:JsonProperty("issuer")
    val issuer: Voter,
    val anchor: AnchorMetadata? = null,
    @param:JsonProperty(value = "vote", required = true)
    @get:JsonProperty("vote")
    val vote: Vote,
    val proposal: UtxoOutputReference? = null,
)
