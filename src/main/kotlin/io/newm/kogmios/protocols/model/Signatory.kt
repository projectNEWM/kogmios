package io.newm.kogmios.protocols.model

import com.fasterxml.jackson.annotation.JsonProperty

data class Signatory(
    @param:JsonProperty(value = "key", required = true)
    @get:JsonProperty("key")
    val key: String,
    @param:JsonProperty(value = "signature", required = true)
    @get:JsonProperty("signature")
    val signature: String,
    val chainCode: String? = null,
    val addressAttributes: String? = null,
)
