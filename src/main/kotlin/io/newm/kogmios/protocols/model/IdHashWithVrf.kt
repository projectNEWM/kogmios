package io.newm.kogmios.protocols.model

import com.fasterxml.jackson.annotation.JsonProperty

data class IdHashWithVrf(
    @param:JsonProperty(value = "id", required = true)
    @get:JsonProperty("id")
    val id: String,
    @param:JsonProperty(value = "vrfVerificationKeyHash", required = true)
    @get:JsonProperty("vrfVerificationKeyHash")
    val vrfVerificationKeyHash: String,
)
