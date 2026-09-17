package io.newm.kogmios.protocols.model

import com.fasterxml.jackson.annotation.JsonProperty

import com.fasterxml.jackson.annotation.JsonTypeInfo

import com.fasterxml.jackson.annotation.JsonTypeName

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.EXISTING_PROPERTY,
    property = "role",
    visible = true,
)
sealed interface GovernanceVoter

@JsonTypeName("genesisDelegate")
data class GovernanceVoterGenesisDelegate(
    @param:JsonProperty(value = "role", required = true)
    @get:JsonProperty("role")
    val role: String,
    @param:JsonProperty(value = "id", required = true)
    @get:JsonProperty("id")
    val id: String,
) : GovernanceVoter

@JsonTypeName("constitutionalCommittee")
data class GovernanceVoterConstitutionalCommittee(
    @param:JsonProperty(value = "role", required = true)
    @get:JsonProperty("role")
    val role: String,
    @param:JsonProperty(value = "id", required = true)
    @get:JsonProperty("id")
    val id: String,
) : GovernanceVoter

@JsonTypeName("delegateRepresentative")
data class GovernanceVoterDelegateRepresentative(
    @param:JsonProperty(value = "role", required = true)
    @get:JsonProperty("role")
    val role: String,
    @param:JsonProperty(value = "id", required = true)
    @get:JsonProperty("id")
    val id: String,
) : GovernanceVoter

@JsonTypeName("stakePoolOperator")
data class GovernanceVoterStakePoolOperator(
    @param:JsonProperty(value = "role", required = true)
    @get:JsonProperty("role")
    val role: String,
    @param:JsonProperty(value = "id", required = true)
    @get:JsonProperty("id")
    val id: String,
) : GovernanceVoter
