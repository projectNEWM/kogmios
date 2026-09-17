package io.newm.kogmios.protocols.model

import com.fasterxml.jackson.annotation.JsonProperty

data class GovernanceProposal(
    val deposit: Ada? = null,
    val returnAccount: String? = null,
    val anchor: AnchorMetadata? = null,
    @param:JsonProperty(value = "action", required = true)
    @get:JsonProperty("action")
    val action: GovernanceAction,
)
