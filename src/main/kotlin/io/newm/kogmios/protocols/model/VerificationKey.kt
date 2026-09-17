package io.newm.kogmios.protocols.model

import com.fasterxml.jackson.annotation.JsonProperty

data class VerificationKey(
    @param:JsonProperty(value = "verificationKey", required = true)
    @get:JsonProperty("verificationKey")
    val verificationKey: String,
)
