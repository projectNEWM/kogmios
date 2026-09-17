package io.newm.kogmios.protocols.model

import com.fasterxml.jackson.annotation.JsonProperty

import java.math.BigInteger

data class BytesSize(
    @param:JsonProperty(value = "bytes", required = true)
    @get:JsonProperty("bytes")
    val bytes: BigInteger,
)
