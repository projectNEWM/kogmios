package io.newm.kogmios.protocols.model

import com.fasterxml.jackson.annotation.JsonProperty

data class UpdateProposal(
    @param:JsonProperty(value = "version", required = true)
    @get:JsonProperty("version")
    val version: Version,
    @param:JsonProperty(value = "software", required = true)
    @get:JsonProperty("software")
    val software: Software,
    @param:JsonProperty(value = "parameters", required = true)
    @get:JsonProperty("parameters")
    val parameters: UpdatableParametersProposal,
    @param:JsonProperty(value = "metadata", required = true)
    @get:JsonProperty("metadata")
    val metadata: Map<String, String>,
)
