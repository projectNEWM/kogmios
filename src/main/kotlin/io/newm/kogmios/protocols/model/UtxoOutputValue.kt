package io.newm.kogmios.protocols.model

import com.fasterxml.jackson.annotation.JsonProperty

data class UtxoOutputValue(
    @param:JsonProperty(value = "ada", required = true)
    @get:JsonProperty("ada")
    val ada: Ada,
    val assets: List<Asset>? = null,
)
