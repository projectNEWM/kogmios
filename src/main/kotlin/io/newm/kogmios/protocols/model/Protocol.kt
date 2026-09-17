package io.newm.kogmios.protocols.model

import com.fasterxml.jackson.annotation.JsonProperty

data class Protocol(
    @param:JsonProperty(value = "version", required = true)
    @get:JsonProperty("version")
    val version: Version,
    @param:JsonProperty(value = "software", required = true)
    @get:JsonProperty("software")
    val software: Software,
    @param:JsonProperty(value = "update", required = true)
    @get:JsonProperty("update")
    val update: Update,
)
