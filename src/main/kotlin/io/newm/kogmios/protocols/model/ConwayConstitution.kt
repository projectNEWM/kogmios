package io.newm.kogmios.protocols.model

import io.newm.kogmios.protocols.model.fault.MetadataHash
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ConwayConstitution(
    @SerialName("guardrails")
    val guardrails: MetadataHash? = null,
    @SerialName("metadata")
    val metadata: AnchorMetadata,
)
