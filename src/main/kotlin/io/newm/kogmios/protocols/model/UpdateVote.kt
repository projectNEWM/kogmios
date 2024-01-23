package io.newm.kogmios.protocols.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdateVote(
    @SerialName("voter")
    val voter: VerificationKey,
    @SerialName("proposal")
    val proposal: UpdateProposalId,
)
