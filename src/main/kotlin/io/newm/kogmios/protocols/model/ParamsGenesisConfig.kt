package io.newm.kogmios.protocols.model

import com.fasterxml.jackson.annotation.JsonProperty

data class ParamsGenesisConfig(
    @param:JsonProperty(value = "era", required = true)
    @get:JsonProperty("era")
    val era: String,
) : Params()
