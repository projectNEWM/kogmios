package io.newm.kogmios.protocols.model

import com.fasterxml.jackson.annotation.JsonProperty

data class VoterReference(
    @param:JsonProperty(value = "id", required = true)
    @get:JsonProperty("id")
    val id: String,
)
