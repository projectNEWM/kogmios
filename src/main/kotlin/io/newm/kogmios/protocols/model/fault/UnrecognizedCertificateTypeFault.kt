package io.newm.kogmios.protocols.model.fault

import com.fasterxml.jackson.annotation.JsonProperty

import com.fasterxml.jackson.annotation.JsonTypeName

/**
 * Unrecognized certificate type. This error is a placeholder due to how internal data-types are modeled. If you ever run into this, please report the issue as you've likely discoverd a critical bug...
 */

@JsonTypeName("3998")
data class UnrecognizedCertificateTypeFault(
    @param:JsonProperty(value = "code", required = true)
    @get:JsonProperty("code")
    override val code: Long,
    @param:JsonProperty(value = "message", required = true)
    @get:JsonProperty("message")
    override val message: String,
    override val data: FaultData? = null,
) : Fault
