package io.newm.kogmios.protocols.model

import com.fasterxml.jackson.annotation.JsonProperty

data class UpdateVote(
    @param:JsonProperty(value = "voter", required = true)
    @get:JsonProperty("voter")
    val voter: VerificationKey,
    @param:JsonProperty(value = "proposal", required = true)
    @get:JsonProperty("proposal")
    val proposal: UpdateProposalId,
)
