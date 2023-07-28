package io.newm.kogmios.protocols.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Update(
    @SerialName("proposal")
    val proposal: UpdateProposal,
    @SerialName("votes")
    val votes: List<UpdateVote>,
)
