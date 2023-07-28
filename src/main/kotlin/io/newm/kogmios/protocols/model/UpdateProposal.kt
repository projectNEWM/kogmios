package io.newm.kogmios.protocols.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdateProposal(
    @SerialName("version")
    val version: Version,
    @SerialName("software")
    val software: Software,
    @SerialName("parameters")
    val parameters: UpdatableParametersProposal,
    @SerialName("metadata")
    val metadata: Map<String, String>,
)
