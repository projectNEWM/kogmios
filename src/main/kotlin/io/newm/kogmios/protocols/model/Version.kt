package io.newm.kogmios.protocols.model

import com.fasterxml.jackson.annotation.JsonProperty

data class Version(
    @param:JsonProperty(value = "major", required = true)
    @get:JsonProperty("major")
    val major: Int,
    @param:JsonProperty(value = "minor", required = true)
    @get:JsonProperty("minor")
    val minor: Int,
    val patch: Int? = null,
)
