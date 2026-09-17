package io.newm.kogmios.protocols.model

import com.fasterxml.jackson.annotation.JsonProperty

data class ConwayConstitutionalCommitteeMember(
    @param:JsonProperty(value = "from", required = true)
    @get:JsonProperty("from")
    val from: String,
    @param:JsonProperty(value = "id", required = true)
    @get:JsonProperty("id")
    val id: String,
    @param:JsonProperty(value = "mandate", required = true)
    @get:JsonProperty("mandate")
    val mandate: Mandate,
)
