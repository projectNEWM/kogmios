package io.newm.kogmios.protocols.model

import com.fasterxml.jackson.annotation.JsonProperty

data class RedeemerPointer(
    @param:JsonProperty(value = "purpose", required = true)
    @get:JsonProperty("purpose")
    val purpose: String,
    @param:JsonProperty(value = "index", required = true)
    @get:JsonProperty("index")
    val index: Int,
)
