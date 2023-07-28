package io.newm.kogmios.protocols.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IssuerPraos(
    @SerialName("verificationKey")
    val verificationKey: String,
    @SerialName("vrfVerificationKey")
    val vrfVerificationKey: String,
    @SerialName("operationalCertificate")
    val operationalCertificate: OperationalCertificatePraos,
    @SerialName("leaderValue")
    val leaderValue: CertifiedVrf,
)
