package io.newm.kogmios.protocols.model.result

import com.fasterxml.jackson.annotation.JsonProperty

data class LastKnownTip(
    @param:JsonProperty(value = "height", required = true)
    @get:JsonProperty("height")
    val height: Long,
    @param:JsonProperty(value = "id", required = true)
    @get:JsonProperty("id")
    val id: String,
    @param:JsonProperty(value = "slot", required = true)
    @get:JsonProperty("slot")
    val slot: Long,
)
