package io.newm.kogmios.protocols.model.fault

import io.newm.kogmios.protocols.model.VoterReference
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * The transaction references an unknown constitutional committee member. This can be either because that member does not actually exist or because it was registered but has resigned. The field 'data.unknownConstitutionalCommitteeMember' indicates what credential is unknown.
 */
@Serializable
@SerialName("3154")
data class UnknownConstitutionalCommitteeMemberFault(
    @SerialName("code")
    override val code: Long,
    @SerialName("message")
    override val message: String,
    @SerialName("data")
    override val data: UnknownConstitutionalCommitteeMemberFaultData,
) : Fault

@Serializable
data class UnknownConstitutionalCommitteeMemberFaultData(
    @SerialName("unknownConstitutionalCommitteeMember")
    val unknownConstitutionalCommitteeMember: VoterReference,
) : FaultData
