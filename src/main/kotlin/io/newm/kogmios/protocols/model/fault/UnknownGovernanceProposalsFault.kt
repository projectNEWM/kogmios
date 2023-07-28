package io.newm.kogmios.protocols.model.fault

import io.newm.kogmios.protocols.model.GovernanceProposalReference
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Reference(s) to unknown governance proposals found in transaction. This may be because you've indicated a wrong identifier or because the proposal hasn't yet been submitted on-chain. Note that the order in which transactions are submitted matters. The field 'data.unknownProposals' tells you about the unknown references.
 */
@Serializable
@SerialName("3138")
data class UnknownGovernanceProposalsFault(
    @SerialName("code")
    override val code: Long,
    @SerialName("message")
    override val message: String,
    @SerialName("data")
    override val data: UnknownGovernanceProposalsFaultData,
) : Fault

@Serializable
data class UnknownGovernanceProposalsFaultData(
    @SerialName("unknownProposals")
    val unknownProposals: List<GovernanceProposalReference>,
) : FaultData
