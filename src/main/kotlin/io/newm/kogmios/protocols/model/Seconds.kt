package io.newm.kogmios.protocols.model

import com.fasterxml.jackson.annotation.JsonProperty

import java.math.BigInteger

data class Seconds(
    @param:JsonProperty(value = "seconds", required = true)
    @get:JsonProperty("seconds")
    val seconds: BigInteger,
)
