package io.newm.kogmios.protocols.model.fault

import io.newm.kogmios.protocols.model.AnchorMetadata
import io.newm.kogmios.protocols.model.UtxoOutputReference
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * The transaction contains invalid or missing reference to previous (ratified) governance proposals. Indeed, some governance proposals such as protocol parameters update or consitutional committee change must point to last action of the same purpose that was ratified. The field 'data.invalidOrMissingPreviousProposals' contains a list of submitted actions that are missing details. For each item, we provide the anchor of the corresponding proposal, the type of previous proposal that is expected and the invalid proposal reference if relevant.
 */
@Serializable
@SerialName("3159")
data class InvalidOrMissingPreviousProposalsFault(
    @SerialName("code")
    override val code: Long,
    @SerialName("message")
    override val message: String,
    @SerialName("data")
    override val data: InvalidOrMissingPreviousProposalsFaultData,
) : Fault

@Serializable
data class InvalidOrMissingPreviousProposalsFaultData(
    @SerialName("invalidOrMissingPreviousProposals")
    val invalidOrMissingPreviousProposals: List<InvalidOrMissingPreviousProposal>,
) : FaultData

@Serializable
data class InvalidOrMissingPreviousProposal(
    @SerialName("anchor")
    val anchor: AnchorMetadata,
    @SerialName("type")
    val type: String,
    @SerialName("invalidPreviousProposal")
    val invalidPreviousProposal: UtxoOutputReference? = null,
)
