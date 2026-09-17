package io.newm.kogmios.protocols.model

import com.fasterxml.jackson.annotation.JsonProperty

data class Kes(
    @param:JsonProperty(value = "period", required = true)
    @get:JsonProperty("period")
    val period: Long,
    @param:JsonProperty(value = "verificationKey", required = true)
    @get:JsonProperty("verificationKey")
    val verificationKey: String,
)
