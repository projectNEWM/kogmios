package io.newm.kogmios.protocols.model

import com.fasterxml.jackson.annotation.JsonProperty

data class GovernanceProposalReference(
    @param:JsonProperty(value = "transaction", required = true)
    @get:JsonProperty("transaction")
    val transaction: Transaction,
    @param:JsonProperty(value = "index", required = true)
    @get:JsonProperty("index")
    val index: Int,
)
