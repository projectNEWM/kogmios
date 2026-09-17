package io.newm.kogmios.protocols.model

import com.fasterxml.jackson.annotation.JsonProperty

import io.newm.kogmios.protocols.model.fault.MetadataHash

data class ConwayConstitution(
    val guardrails: MetadataHash? = null,
    @param:JsonProperty(value = "metadata", required = true)
    @get:JsonProperty("metadata")
    val metadata: AnchorMetadata,
)
