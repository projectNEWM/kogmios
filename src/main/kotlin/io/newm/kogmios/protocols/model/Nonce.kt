package io.newm.kogmios.protocols.model

import com.fasterxml.jackson.annotation.JsonProperty

data class Nonce(
    @param:JsonProperty(value = "proof", required = true)
    @get:JsonProperty("proof")
    val proof: String,
    @param:JsonProperty(value = "output", required = true)
    @get:JsonProperty("output")
    val output: String,
)
