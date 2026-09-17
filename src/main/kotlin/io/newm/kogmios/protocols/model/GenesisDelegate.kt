package io.newm.kogmios.protocols.model

import com.fasterxml.jackson.annotation.JsonProperty

data class GenesisDelegate(
    @param:JsonProperty(value = "issuer", required = true)
    @get:JsonProperty("issuer")
    val issuer: IdHash,
    @param:JsonProperty(value = "delegate", required = true)
    @get:JsonProperty("delegate")
    val delegate: IdHashWithVrf
)
