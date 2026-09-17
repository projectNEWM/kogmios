package io.newm.kogmios.protocols.model.fault

import com.fasterxml.jackson.annotation.JsonProperty

import com.fasterxml.jackson.annotation.JsonTypeName

import io.newm.kogmios.protocols.model.DelegateRepresentative

/**
 * The transaction references an unknown delegate representative. To delegate to a representative, it must first register as such. This may be done in the same transaction or in an earlier transaction but cannot happen retro-actively. The field 'data.unknownDelegateRepresentative' indicates what credential is used without being registered.
 */

@JsonTypeName("3153")
data class DRepNotRegisteredFault(
    @param:JsonProperty(value = "code", required = true)
    @get:JsonProperty("code")
    override val code: Long,
    @param:JsonProperty(value = "message", required = true)
    @get:JsonProperty("message")
    override val message: String,
    @param:JsonProperty(value = "data", required = true)
    @get:JsonProperty("data")
    override val data: DRepNotRegisteredFaultData,
) : Fault

data class DRepNotRegisteredFaultData(
    @param:JsonProperty(value = "unknownDelegateRepresentative", required = true)
    @get:JsonProperty("unknownDelegateRepresentative")
    val unknownDelegateRepresentative: DelegateRepresentative,
) : FaultData
