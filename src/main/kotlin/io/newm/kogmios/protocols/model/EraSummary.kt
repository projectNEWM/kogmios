package io.newm.kogmios.protocols.model

import com.fasterxml.jackson.annotation.JsonProperty

import io.newm.kogmios.protocols.model.result.Bound

data class EraSummary(
    @param:JsonProperty(value = "start", required = true)
    @get:JsonProperty("start")
    val start: Bound,
    @param:JsonProperty(value = "end", required = true)
    @get:JsonProperty("end")
    val end: Bound?,
    @param:JsonProperty(value = "parameters", required = true)
    @get:JsonProperty("parameters")
    val parameters: EraParameters,
)
