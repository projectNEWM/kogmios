package io.newm.kogmios.protocols.model

import com.fasterxml.jackson.annotation.JsonProperty

import java.math.BigInteger

data class Asset(
    @param:JsonProperty(value = "policyId", required = true)
    @get:JsonProperty("policyId")
    val policyId: String,
    @param:JsonProperty(value = "name", required = true)
    @get:JsonProperty("name")
    val name: String,
    @param:JsonProperty(value = "quantity", required = true)
    @get:JsonProperty("quantity")
    val quantity: BigInteger,
)
