package io.newm.kogmios.protocols.model

import com.fasterxml.jackson.annotation.JsonProperty

data class MinFeeReferenceScripts(
    @param:JsonProperty(value = "range", required = true)
    @get:JsonProperty("range")
    val range: Int,
    @param:JsonProperty(value = "base", required = true)
    @get:JsonProperty("base")
    val base: Double,
    @param:JsonProperty(value = "multiplier", required = true)
    @get:JsonProperty("multiplier")
    val multiplier: Double,
)
