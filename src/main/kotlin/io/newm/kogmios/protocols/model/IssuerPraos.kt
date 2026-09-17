package io.newm.kogmios.protocols.model

import com.fasterxml.jackson.annotation.JsonProperty

data class IssuerPraos(
    @param:JsonProperty(value = "verificationKey", required = true)
    @get:JsonProperty("verificationKey")
    val verificationKey: String,
    @param:JsonProperty(value = "vrfVerificationKey", required = true)
    @get:JsonProperty("vrfVerificationKey")
    val vrfVerificationKey: String,
    @param:JsonProperty(value = "operationalCertificate", required = true)
    @get:JsonProperty("operationalCertificate")
    val operationalCertificate: OperationalCertificatePraos,
    @param:JsonProperty(value = "leaderValue", required = true)
    @get:JsonProperty("leaderValue")
    val leaderValue: CertifiedVrf,
)
