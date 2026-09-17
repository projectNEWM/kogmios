package io.newm.kogmios.protocols.model

import com.fasterxml.jackson.annotation.JsonProperty

data class Ada(
    @param:JsonProperty(value = "ada", required = true)
    @get:JsonProperty("ada")
    val ada: Lovelace,
)
