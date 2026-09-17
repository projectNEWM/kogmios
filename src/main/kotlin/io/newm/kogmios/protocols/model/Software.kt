package io.newm.kogmios.protocols.model

import com.fasterxml.jackson.annotation.JsonProperty

data class Software(
    @param:JsonProperty(value = "appName", required = true)
    @get:JsonProperty("appName")
    val appName: String,
    @param:JsonProperty(value = "number", required = true)
    @get:JsonProperty("number")
    val number: Long,
)
