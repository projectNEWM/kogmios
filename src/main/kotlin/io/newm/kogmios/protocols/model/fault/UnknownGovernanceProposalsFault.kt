package io.newm.kogmios.protocols.model.fault

import com.fasterxml.jackson.annotation.JsonProperty

import com.fasterxml.jackson.annotation.JsonTypeName

import io.newm.kogmios.protocols.model.GovernanceProposalReference

/**
 * Reference(s) to unknown governance proposals found in transaction. This may be because you've indicated a wrong identifier or because the proposal hasn't yet been submitted on-chain. Note that the order in which transactions are submitted matters. The field 'data.unknownProposals' tells you about the unknown references.
 */

@JsonTypeName("3138")
data class UnknownGovernanceProposalsFault(
    @param:JsonProperty(value = "code", required = true)
    @get:JsonProperty("code")
    override val code: Long,
    @param:JsonProperty(value = "message", required = true)
    @get:JsonProperty("message")
    override val message: String,
    @param:JsonProperty(value = "data", required = true)
    @get:JsonProperty("data")
    override val data: UnknownGovernanceProposalsFaultData,
) : Fault

data class UnknownGovernanceProposalsFaultData(
    @param:JsonProperty(value = "unknownProposals", required = true)
    @get:JsonProperty("unknownProposals")
    val unknownProposals: List<GovernanceProposalReference>,
) : FaultData
