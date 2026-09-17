package io.newm.kogmios.protocols.model

import com.fasterxml.jackson.annotation.JsonProperty

data class MetadataResult(
    @param:JsonProperty(value = "url", required = true)
    @get:JsonProperty("url")
    val url: String,
    @param:JsonProperty(value = "hash", required = true)
    @get:JsonProperty("hash")
    val hash: String,
)
