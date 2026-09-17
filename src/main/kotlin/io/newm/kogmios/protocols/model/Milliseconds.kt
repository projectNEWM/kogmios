package io.newm.kogmios.protocols.model

import com.fasterxml.jackson.annotation.JsonProperty

import java.math.BigInteger

data class Milliseconds(
    @param:JsonProperty(value = "milliseconds", required = true)
    @get:JsonProperty("milliseconds")
    val milliseconds: BigInteger,
)
