package io.newm.kogmios.protocols.model

import com.fasterxml.jackson.annotation.JsonProperty

data class Update(
    @param:JsonProperty(value = "proposal", required = true)
    @get:JsonProperty("proposal")
    val proposal: UpdateProposal,
    @param:JsonProperty(value = "votes", required = true)
    @get:JsonProperty("votes")
    val votes: List<UpdateVote>,
)
