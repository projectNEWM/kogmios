package io.newm.kogmios.protocols.model

import com.fasterxml.jackson.annotation.JsonProperty

data class TxShelleyBootstrap(
    @param:JsonProperty(value = "signature", required = true)
    @get:JsonProperty("signature")
    val signature: String,
    @param:JsonProperty(value = "key", required = true)
    @get:JsonProperty("key")
    val key: String,
    @param:JsonProperty(value = "chainCode", required = true)
    @get:JsonProperty("chainCode")
    val chainCode: String?,
    @param:JsonProperty(value = "addressAttributes", required = true)
    @get:JsonProperty("addressAttributes")
    val addressAttributes: String?,
)
