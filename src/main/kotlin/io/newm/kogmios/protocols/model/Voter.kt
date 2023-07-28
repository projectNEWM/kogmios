package io.newm.kogmios.protocols.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonClassDiscriminator

@Serializable
@JsonClassDiscriminator("role")
sealed interface Voter

@Serializable
@SerialName("genesisDelegate")
data class GenesisDelegateVoter(
    @SerialName("id")
    val id: String,
) : Voter

@Serializable
@SerialName("constitutionalCommittee")
data class ConstitutionalCommitteeVoter(
    @SerialName("id")
    val id: String,
) : Voter

@Serializable
@SerialName("delegateRepresentative")
data class DelegateRepresentativeVoter(
    @SerialName("id")
    val id: String,
) : Voter

@Serializable
@SerialName("stakePoolOperator")
data class StakePoolOperatorVoter(
    @SerialName("id")
    val id: String,
) : Voter
