package io.newm.kogmios.protocols.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GovernanceProposal(
    @SerialName("deposit")
    val deposit: Ada? = null,
    @SerialName("returnAccount")
    val returnAccount: String? = null,
    @SerialName("anchor")
    val anchor: AnchorMetadata? = null,
    @SerialName("action")
    val action: GovernanceAction,
)
