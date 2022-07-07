package io.newm.kogmios.protocols.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CertifiedVrf(
    @SerialName("output")
    val output: String,
    @SerialName("proof")
    val proof: String
)
