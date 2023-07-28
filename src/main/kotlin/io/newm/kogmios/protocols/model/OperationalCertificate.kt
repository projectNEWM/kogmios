package io.newm.kogmios.protocols.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OperationalCertificate(
    @SerialName("issuer")
    val issuer: VerificationKey,
    @SerialName("delegate")
    val delegate: VerificationKey,
)
