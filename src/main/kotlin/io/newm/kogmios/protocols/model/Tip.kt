package io.newm.kogmios.protocols.model

import com.fasterxml.jackson.annotation.JsonProperty

data class Tip(
    @param:JsonProperty(value = "slot", required = true)
    @get:JsonProperty("slot")
    val slot: Long,
    @param:JsonProperty(value = "id", required = true)
    @get:JsonProperty("id")
    val id: String,
    @param:JsonProperty(value = "height", required = true)
    @get:JsonProperty("height")
    val height: Long,
)
