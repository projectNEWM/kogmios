package io.newm.kogmios.protocols.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Nonce(
    @SerialName("proof")
    val proof: String,
    @SerialName("output")
    val output: String,
)
