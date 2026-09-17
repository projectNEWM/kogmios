package io.newm.kogmios.protocols.model

import com.fasterxml.jackson.annotation.JsonProperty

data class GuardrailsHash(
    @param:JsonProperty(value = "hash", required = true)
    @get:JsonProperty("hash")
    val hash: String,
)
