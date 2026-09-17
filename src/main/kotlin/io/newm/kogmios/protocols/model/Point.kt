package io.newm.kogmios.protocols.model

import com.fasterxml.jackson.annotation.JsonProperty

data class Point(
    @param:JsonProperty(value = "point", required = true)
    @get:JsonProperty("point")
    val point: PointDetail,
) : PointOrOrigin()
