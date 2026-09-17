package io.newm.kogmios.protocols.model.fault

import com.fasterxml.jackson.annotation.JsonProperty

import com.fasterxml.jackson.annotation.JsonTypeName

/**
 * The transaction references an unknown stake credential. For example, to delegate to a stake pool, you must first register the stake key or script used for delegation. This may be done in the same transaction or in an earlier transaction but cannot happen retro-actively. The field 'data.unknownCredential' indicates what credential is used without being registered.
 */

@JsonTypeName("3146")
data class UnknownCredentialFault(
    @param:JsonProperty(value = "code", required = true)
    @get:JsonProperty("code")
    override val code: Long,
    @param:JsonProperty(value = "message", required = true)
    @get:JsonProperty("message")
    override val message: String,
    @param:JsonProperty(value = "data", required = true)
    @get:JsonProperty("data")
    override val data: UnknownCredentialFaultData,
) : Fault

data class UnknownCredentialFaultData(
    @param:JsonProperty(value = "unknownCredential", required = true)
    @get:JsonProperty("unknownCredential")
    val unknownCredential: String,
) : FaultData
