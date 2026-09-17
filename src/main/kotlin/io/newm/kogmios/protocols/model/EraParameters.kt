package io.newm.kogmios.protocols.model

import com.fasterxml.jackson.annotation.JsonProperty

data class EraParameters(
    @param:JsonProperty(value = "epochLength", required = true)
    @get:JsonProperty("epochLength")
    val epochLength: Long,
    @param:JsonProperty(value = "slotLength", required = true)
    @get:JsonProperty("slotLength")
    val slotLength: Milliseconds,
    val safeZone: Long? = null,
)
