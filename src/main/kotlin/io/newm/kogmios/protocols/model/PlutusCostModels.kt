package io.newm.kogmios.protocols.model

import com.fasterxml.jackson.annotation.JsonProperty

import java.math.BigInteger

data class PlutusCostModels(
    @param:JsonProperty("plutus:v1")
    @get:JsonProperty("plutus:v1")
    val plutusV1: List<BigInteger>? = null,
    @param:JsonProperty("plutus:v2")
    @get:JsonProperty("plutus:v2")
    val plutusV2: List<BigInteger>? = null,
    @param:JsonProperty("plutus:v3")
    @get:JsonProperty("plutus:v3")
    val plutusV3: List<BigInteger>? = null,
)
