package io.newm.kogmios.protocols.model.fault

import com.fasterxml.jackson.annotation.JsonProperty

import com.fasterxml.jackson.annotation.JsonTypeName

import io.newm.kogmios.protocols.model.AnchorMetadata
import io.newm.kogmios.protocols.model.UtxoOutputReference

/**
 * The transaction contains invalid or missing reference to previous (ratified) governance proposals. Indeed, some governance proposals such as protocol parameters update or consitutional committee change must point to last action of the same purpose that was ratified. The field 'data.invalidOrMissingPreviousProposals' contains a list of submitted actions that are missing details. For each item, we provide the anchor of the corresponding proposal, the type of previous proposal that is expected and the invalid proposal reference if relevant.
 */

@JsonTypeName("3159")
data class InvalidOrMissingPreviousProposalsFault(
    @param:JsonProperty(value = "code", required = true)
    @get:JsonProperty("code")
    override val code: Long,
    @param:JsonProperty(value = "message", required = true)
    @get:JsonProperty("message")
    override val message: String,
    @param:JsonProperty(value = "data", required = true)
    @get:JsonProperty("data")
    override val data: InvalidOrMissingPreviousProposalsFaultData,
) : Fault

data class InvalidOrMissingPreviousProposalsFaultData(
    @param:JsonProperty(value = "invalidOrMissingPreviousProposals", required = true)
    @get:JsonProperty("invalidOrMissingPreviousProposals")
    val invalidOrMissingPreviousProposals: List<InvalidOrMissingPreviousProposal>,
) : FaultData

data class InvalidOrMissingPreviousProposal(
    @param:JsonProperty(value = "anchor", required = true)
    @get:JsonProperty("anchor")
    val anchor: AnchorMetadata,
    @param:JsonProperty(value = "type", required = true)
    @get:JsonProperty("type")
    val type: String,
    val invalidPreviousProposal: UtxoOutputReference? = null,
)
