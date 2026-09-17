package io.newm.kogmios.protocols.model

import com.fasterxml.jackson.annotation.JsonProperty

data class UtxoOutput(
    @param:JsonProperty(value = "address", required = true)
    @get:JsonProperty("address")
    val address: String,
    @param:JsonProperty(value = "value", required = true)
    @get:JsonProperty("value")
    val value: UtxoOutputValue,
    val datumHash: String? = null,
    val datum: String? = null,
    val script: Script? = null,
)
