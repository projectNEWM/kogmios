package io.newm.kogmios.protocols.model

import com.fasterxml.jackson.annotation.JsonProperty

data class ProtocolUpdate(
    @param:JsonProperty(value = "epoch", required = true)
    @get:JsonProperty("epoch")
    val epoch: Long,
    @param:JsonProperty(value = "proposal", required = true)
    @get:JsonProperty("proposal")
    val proposal: Map<String, UpdateProposal>,
)
