package io.newm.kogmios.protocols.model.fault

import com.fasterxml.jackson.annotation.JsonProperty

import com.fasterxml.jackson.annotation.JsonTypeName

import io.newm.kogmios.protocols.model.VoterReference

/**
 * The transaction references an unknown constitutional committee member. This can be either because that member does not actually exist or because it was registered but has resigned. The field 'data.unknownConstitutionalCommitteeMember' indicates what credential is unknown.
 */

@JsonTypeName("3154")
data class UnknownConstitutionalCommitteeMemberFault(
    @param:JsonProperty(value = "code", required = true)
    @get:JsonProperty("code")
    override val code: Long,
    @param:JsonProperty(value = "message", required = true)
    @get:JsonProperty("message")
    override val message: String,
    @param:JsonProperty(value = "data", required = true)
    @get:JsonProperty("data")
    override val data: UnknownConstitutionalCommitteeMemberFaultData,
) : Fault

data class UnknownConstitutionalCommitteeMemberFaultData(
    @param:JsonProperty(value = "unknownConstitutionalCommitteeMember", required = true)
    @get:JsonProperty("unknownConstitutionalCommitteeMember")
    val unknownConstitutionalCommitteeMember: VoterReference,
) : FaultData
