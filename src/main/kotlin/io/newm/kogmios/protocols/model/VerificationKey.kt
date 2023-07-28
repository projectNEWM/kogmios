package io.newm.kogmios.protocols.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VerificationKey(
    @SerialName("verificationKey")
    val verificationKey: String,
)
