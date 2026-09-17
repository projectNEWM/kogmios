package io.newm.kogmios.protocols.model

import com.fasterxml.jackson.annotation.JsonProperty

data class OperationalCertificate(
    @param:JsonProperty(value = "issuer", required = true)
    @get:JsonProperty("issuer")
    val issuer: VerificationKey,
    @param:JsonProperty(value = "delegate", required = true)
    @get:JsonProperty("delegate")
    val delegate: VerificationKey,
)
