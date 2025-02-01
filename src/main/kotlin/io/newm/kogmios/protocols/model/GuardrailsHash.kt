package io.newm.kogmios.protocols.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GuardrailsHash(
    @SerialName("hash")
    val hash: String,
)
