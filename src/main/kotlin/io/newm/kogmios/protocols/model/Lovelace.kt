package io.newm.kogmios.protocols.model

import com.fasterxml.jackson.annotation.JsonProperty

import java.math.BigInteger

data class Lovelace(
    @param:JsonProperty(value = "lovelace", required = true)
    @get:JsonProperty("lovelace")
    val lovelace: BigInteger,
)
