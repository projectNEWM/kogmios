package io.newm.kogmios.protocols.model.fault

import com.fasterxml.jackson.annotation.JsonProperty

import com.fasterxml.jackson.annotation.JsonTypeName

import io.newm.kogmios.protocols.model.VoterReference

/**
 * The transaction contains an invalid governance action: it tries to both add members to the committee and remove some of those same members. The field 'data.conflictingMembers' indicates which members are found on both sides.
 */

@JsonTypeName("3156")
data class ConflictingCommitteeUpdateFault(
    @param:JsonProperty(value = "code", required = true)
    @get:JsonProperty("code")
    override val code: Long,
    @param:JsonProperty(value = "message", required = true)
    @get:JsonProperty("message")
    override val message: String,
    @param:JsonProperty(value = "data", required = true)
    @get:JsonProperty("data")
    override val data: ConflictingCommitteeUpdateFaultData,
) : Fault

data class ConflictingCommitteeUpdateFaultData(
    @param:JsonProperty(value = "conflictingMembers", required = true)
    @get:JsonProperty("conflictingMembers")
    val conflictingMembers: List<VoterReference>,
) : FaultData
