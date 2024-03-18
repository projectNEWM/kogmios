package io.newm.kogmios.protocols.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ConwayConstitution(
    @SerialName("guardrails")
    val guardrails: String? = null,
    @SerialName("metadata")
    val metadata: AnchorMetadata,
)
