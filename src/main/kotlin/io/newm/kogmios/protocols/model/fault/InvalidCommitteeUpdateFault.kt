package io.newm.kogmios.protocols.model.fault

import com.fasterxml.jackson.annotation.JsonProperty

import com.fasterxml.jackson.annotation.JsonTypeName

import io.newm.kogmios.protocols.model.VoterReference

/**
 * The transaction contains an invalid governance action: it tries to add new members to the constitutional committee with a retirement epoch in the past. The field 'data.alreadyRetiredMembers' indicates the faulty members that would otherwise be already retired.
 */

@JsonTypeName("3157")
data class InvalidCommitteeUpdateFault(
    @param:JsonProperty(value = "code", required = true)
    @get:JsonProperty("code")
    override val code: Long,
    @param:JsonProperty(value = "message", required = true)
    @get:JsonProperty("message")
    override val message: String,
    @param:JsonProperty(value = "data", required = true)
    @get:JsonProperty("data")
    override val data: InvalidCommitteeUpdateFaultData,
) : Fault

data class InvalidCommitteeUpdateFaultData(
    @param:JsonProperty(value = "alreadyRetiredMembers", required = true)
    @get:JsonProperty("alreadyRetiredMembers")
    val alreadyRetiredMembers: List<VoterReference>,
) : FaultData
