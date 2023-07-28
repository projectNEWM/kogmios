package io.newm.kogmios.protocols.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonClassDiscriminator
import org.apache.commons.numbers.fraction.BigFraction

@Serializable
@JsonClassDiscriminator("type")
sealed interface GovernanceAction

@Serializable
@SerialName("protocolParametersUpdate")
data class ProtocolParametersUpdateGovernanceAction(
    @SerialName("parameters")
    val parameters: ProposedProtocolParameters,
) : GovernanceAction

@Serializable
@SerialName("hardForkInitiation")
data class HardForkInitiationGovernanceAction(
    @SerialName("version")
    val version: Version,
) : GovernanceAction

@Serializable
@SerialName("treasuryTransfer")
data class TreasuryTransferGovernanceAction(
    @SerialName("source")
    val source: String,
    @SerialName("target")
    val target: String,
    @SerialName("value")
    val value: Ada,
) : GovernanceAction

@Serializable
@SerialName("treasuryWithdrawals")
data class TreasuryWithdrawalsGovernanceAction(
    @SerialName("withdrawals")
    val rewards: Map<String, Ada>,
) : GovernanceAction

@Serializable
@SerialName("constitutionalCommittee")
data class ConstitutionalCommitteeGovernanceAction(
    @SerialName("members")
    val members: ConstitutionalCommitteeMembers,
    @SerialName("quorum")
    @Contextual
    val quorum: BigFraction,
) : GovernanceAction

@Serializable
data class ConstitutionalCommitteeMembers(
    @SerialName("added")
    val added: List<AddedConstitutionalCommitteeMember>,
    @SerialName("removed")
    val removed: List<IdHash>,
)

@Serializable
data class AddedConstitutionalCommitteeMember(
    @SerialName("id")
    val id: String,
    @SerialName("mandate")
    val mandate: Mandate,
)

@Serializable
data class Mandate(
    @SerialName("epoch")
    val epoch: Long,
)

@Serializable
@SerialName("constitution")
data class ConstitutionGovernanceAction(
    @SerialName("hash")
    val hash: String,
    @SerialName("anchor")
    val anchor: AnchorMetadata,
) : GovernanceAction

@Serializable
@SerialName("noConfidence")
data object NoConfidenceGovernanceAction : GovernanceAction

@Serializable
@SerialName("information")
data object InformationGovernanceAction : GovernanceAction
