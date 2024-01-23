package io.newm.kogmios.protocols.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GovernanceVote(
    @SerialName("issuer")
    val issuer: Voter,
    @SerialName("anchor")
    val anchor: AnchorMetadata? = null,
    @SerialName("vote")
    val vote: Vote,
    @SerialName("proposal")
    val proposal: UtxoOutputReference? = null,
)
