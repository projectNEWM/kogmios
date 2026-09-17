package io.newm.kogmios.protocols.model.fault

import com.fasterxml.jackson.annotation.JsonProperty

import com.fasterxml.jackson.annotation.JsonTypeName

/**
 * Trying to re-register some already known credentials. Stake credentials can only be registered once. This is true for both keys and scripts. The field 'data.knownCredential' points to an already known credential that's being re-registered by this transaction.
 */

@JsonTypeName("3145")
data class CredentialAlreadyRegisteredFault(
    @param:JsonProperty(value = "code", required = true)
    @get:JsonProperty("code")
    override val code: Long,
    @param:JsonProperty(value = "message", required = true)
    @get:JsonProperty("message")
    override val message: String,
    @param:JsonProperty(value = "data", required = true)
    @get:JsonProperty("data")
    override val data: CredentialAlreadyRegisteredFaultData,
) : Fault

data class CredentialAlreadyRegisteredFaultData(
    @param:JsonProperty(value = "knownCredential", required = true)
    @get:JsonProperty("knownCredential")
    val knownCredential: String,
) : FaultData
