package io.newm.kogmios.protocols.model

import com.fasterxml.jackson.annotation.JsonProperty

data class Validator(
    @param:JsonProperty(value = "index", required = true)
    @get:JsonProperty("index")
    val index: Int,
    @param:JsonProperty(value = "purpose", required = true)
    @get:JsonProperty("purpose")
    val purpose: String,
)
