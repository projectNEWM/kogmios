package io.newm.kogmios.protocols.model.fault

import io.newm.kogmios.protocols.model.VoterReference
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * The transaction contains an invalid governance action: it tries to add new members to the constitutional committee with a retirement epoch in the past. The field 'data.alreadyRetiredMembers' indicates the faulty members that would otherwise be already retired.
 */
@Serializable
@SerialName("3157")
data class InvalidCommitteeUpdateFault(
    @SerialName("code")
    override val code: Long,
    @SerialName("message")
    override val message: String,
    @SerialName("data")
    override val data: InvalidCommitteeUpdateFaultData,
) : Fault

@Serializable
data class InvalidCommitteeUpdateFaultData(
    @SerialName("alreadyRetiredMembers")
    val alreadyRetiredMembers: List<VoterReference>,
) : FaultData
