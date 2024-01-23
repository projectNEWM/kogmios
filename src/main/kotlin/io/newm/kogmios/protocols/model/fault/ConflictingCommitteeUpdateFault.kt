package io.newm.kogmios.protocols.model.fault

import io.newm.kogmios.protocols.model.VoterReference
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * The transaction contains an invalid governance action: it tries to both add members to the committee and remove some of those same members. The field 'data.conflictingMembers' indicates which members are found on both sides.
 */
@Serializable
@SerialName("3156")
data class ConflictingCommitteeUpdateFault(
    @SerialName("code")
    override val code: Long,
    @SerialName("message")
    override val message: String,
    @SerialName("data")
    override val data: ConflictingCommitteeUpdateFaultData,
) : Fault

@Serializable
data class ConflictingCommitteeUpdateFaultData(
    @SerialName("conflictingMembers")
    val conflictingMembers: List<VoterReference>,
) : FaultData
