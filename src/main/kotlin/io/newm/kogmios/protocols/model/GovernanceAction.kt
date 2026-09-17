package io.newm.kogmios.protocols.model

import com.fasterxml.jackson.annotation.JsonProperty

import com.fasterxml.jackson.annotation.JsonTypeInfo

import com.fasterxml.jackson.annotation.JsonTypeName

import org.apache.commons.numbers.fraction.BigFraction

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
sealed interface GovernanceAction

@JsonTypeName("protocolParametersUpdate")
data class ProtocolParametersUpdateGovernanceAction(
    val ancestor: UtxoInput? = null,
    @param:JsonProperty(value = "parameters", required = true)
    @get:JsonProperty("parameters")
    val parameters: ProposedProtocolParameters,
    val guardrails: GuardrailsHash? = null,
) : GovernanceAction

@JsonTypeName("hardForkInitiation")
data class HardForkInitiationGovernanceAction(
    val ancestor: UtxoInput? = null,
    @param:JsonProperty(value = "version", required = true)
    @get:JsonProperty("version")
    val version: Version,
) : GovernanceAction

@JsonTypeName("treasuryTransfer")
data class TreasuryTransferGovernanceAction(
    @param:JsonProperty(value = "source", required = true)
    @get:JsonProperty("source")
    val source: String,
    @param:JsonProperty(value = "target", required = true)
    @get:JsonProperty("target")
    val target: String,
    @param:JsonProperty(value = "value", required = true)
    @get:JsonProperty("value")
    val value: Ada,
) : GovernanceAction

@JsonTypeName("treasuryWithdrawals")
data class TreasuryWithdrawalsGovernanceAction(
    @param:JsonProperty(value = "withdrawals", required = true)
    @get:JsonProperty("withdrawals")
    val withdrawals: Map<String, Ada>,
    val guardrails: GuardrailsHash? = null,
) : GovernanceAction

@JsonTypeName("constitutionalCommittee")
data class ConstitutionalCommitteeGovernanceAction(
    val ancestor: UtxoInput? = null,
    @param:JsonProperty(value = "members", required = true)
    @get:JsonProperty("members")
    val members: ConstitutionalCommitteeMembers,
    @param:JsonProperty(value = "quorum", required = true)
    @get:JsonProperty("quorum")
    val quorum: BigFraction,
) : GovernanceAction

data class ConstitutionalCommitteeMembers(
    @param:JsonProperty(value = "added", required = true)
    @get:JsonProperty("added")
    val added: List<AddedConstitutionalCommitteeMember>,
    @param:JsonProperty(value = "removed", required = true)
    @get:JsonProperty("removed")
    val removed: List<IdHash>,
)

data class AddedConstitutionalCommitteeMember(
    @param:JsonProperty(value = "id", required = true)
    @get:JsonProperty("id")
    val id: String,
    @param:JsonProperty(value = "from", required = true)
    @get:JsonProperty("from")
    val from: String,
    @param:JsonProperty(value = "mandate", required = true)
    @get:JsonProperty("mandate")
    val mandate: Mandate,
)

data class Mandate(
    @param:JsonProperty(value = "epoch", required = true)
    @get:JsonProperty("epoch")
    val epoch: Long,
)

@JsonTypeName("constitution")
data class ConstitutionGovernanceAction(
    val ancestor: UtxoInput? = null,
    @param:JsonProperty(value = "guardrails", required = true)
    @get:JsonProperty("guardrails")
    val guardrails: GuardrailsHash?,
    @param:JsonProperty(value = "metadata", required = true)
    @get:JsonProperty("metadata")
    val metadata: AnchorMetadata,
) : GovernanceAction

@JsonTypeName("noConfidence")
data class NoConfidenceGovernanceAction(
    val ancestor: UtxoInput? = null,
) : GovernanceAction

@JsonTypeName("information")
data object InformationGovernanceAction : GovernanceAction
