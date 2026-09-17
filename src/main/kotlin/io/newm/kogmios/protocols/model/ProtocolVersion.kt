package io.newm.kogmios.protocols.model

import com.fasterxml.jackson.annotation.JsonProperty

data class ProtocolVersion(
    @param:JsonProperty(value = "version", required = true)
    @get:JsonProperty("version")
    val version: Version,
)
