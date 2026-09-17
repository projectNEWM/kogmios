package io.newm.kogmios.protocols.model.fault

import com.fasterxml.jackson.annotation.JsonProperty

import com.fasterxml.jackson.annotation.JsonTypeName

/**
 * Invalid or unauthorized genesis delegation. The genesis delegate is unknown, invalid or already in use.
 */

@JsonTypeName("3148")
data class InvalidGenesisDelegationFault(
    @param:JsonProperty(value = "code", required = true)
    @get:JsonProperty("code")
    override val code: Long,
    @param:JsonProperty(value = "message", required = true)
    @get:JsonProperty("message")
    override val message: String,
    override val data: FaultData? = null,
) : Fault
