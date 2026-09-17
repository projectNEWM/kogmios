package io.newm.kogmios.protocols.model

import com.fasterxml.jackson.annotation.JsonProperty

data class CertifiedVrf(
    @param:JsonProperty(value = "output", required = true)
    @get:JsonProperty("output")
    val output: String,
    @param:JsonProperty(value = "proof", required = true)
    @get:JsonProperty("proof")
    val proof: String,
)
