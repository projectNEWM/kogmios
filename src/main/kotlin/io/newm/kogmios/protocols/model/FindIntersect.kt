package io.newm.kogmios.protocols.model

import com.fasterxml.jackson.annotation.JsonProperty

data class FindIntersect(
    @param:JsonProperty(value = "points", required = true)
    @get:JsonProperty("points")
    val points: List<PointDetailOrOrigin>,
)
