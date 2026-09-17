package io.newm.kogmios.protocols.model.result

import com.fasterxml.jackson.annotation.JsonProperty

data class SessionDurations(
    @param:JsonProperty(value = "max", required = true)
    @get:JsonProperty("max")
    val max: Double,
    @param:JsonProperty(value = "mean", required = true)
    @get:JsonProperty("mean")
    val mean: Double,
    @param:JsonProperty(value = "min", required = true)
    @get:JsonProperty("min")
    val min: Double,
)
