package io.newm.kogmios.protocols.model

import com.fasterxml.jackson.annotation.JsonProperty

data class TransactionMetadata(
    @param:JsonProperty(value = "hash", required = true)
    @get:JsonProperty("hash")
    val hash: String,
    @param:JsonProperty(value = "labels", required = true)
    @get:JsonProperty("labels")
    val labels: Map<String, Label>,
)
