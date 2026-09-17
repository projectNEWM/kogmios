package io.newm.kogmios.protocols.model.fault

import com.fasterxml.jackson.annotation.JsonProperty

import com.fasterxml.jackson.annotation.JsonTypeName

import io.newm.kogmios.protocols.model.DelegateRepresentative

/**
 * Trying to re-register some already known delegate representative. Delegate representatives can only be registered once. The field 'data.knownDelegateRepresentatives' points to an already known credential that's being re-registered by this transaction.
 */

@JsonTypeName("3152")
data class DRepAlreadyRegisteredFault(
    @param:JsonProperty(value = "code", required = true)
    @get:JsonProperty("code")
    override val code: Long,
    @param:JsonProperty(value = "message", required = true)
    @get:JsonProperty("message")
    override val message: String,
    @param:JsonProperty(value = "data", required = true)
    @get:JsonProperty("data")
    override val data: DRepAlreadyRegisteredFaultData,
) : Fault

data class DRepAlreadyRegisteredFaultData(
    @param:JsonProperty(value = "knownDelegateRepresentative", required = true)
    @get:JsonProperty("knownDelegateRepresentative")
    val knownDelegateRepresentative: DelegateRepresentative,
) : FaultData
