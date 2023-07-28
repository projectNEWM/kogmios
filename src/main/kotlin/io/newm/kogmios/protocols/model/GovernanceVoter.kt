package io.newm.kogmios.protocols.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonClassDiscriminator

@Serializable
@JsonClassDiscriminator("role")
sealed interface GovernanceVoter

@Serializable
@SerialName("genesisDelegate")
data class GovernanceVoterGenesisDelegate(
    @SerialName("role")
    val role: String,
    @SerialName("id")
    val id: String,
) : GovernanceVoter

@Serializable
@SerialName("constitutionalCommittee")
data class GovernanceVoterConstitutionalCommittee(
    @SerialName("role")
    val role: String,
    @SerialName("id")
    val id: String,
) : GovernanceVoter

@Serializable
@SerialName("delegateRepresentative")
data class GovernanceVoterDelegateRepresentative(
    @SerialName("role")
    val role: String,
    @SerialName("id")
    val id: String,
) : GovernanceVoter

@Serializable
@SerialName("stakePoolOperator")
data class GovernanceVoterStakePoolOperator(
    @SerialName("role")
    val role: String,
    @SerialName("id")
    val id: String,
) : GovernanceVoter
