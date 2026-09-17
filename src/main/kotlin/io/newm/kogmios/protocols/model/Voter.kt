package io.newm.kogmios.protocols.model

import com.fasterxml.jackson.annotation.JsonProperty

import com.fasterxml.jackson.annotation.JsonTypeInfo

import com.fasterxml.jackson.annotation.JsonTypeName

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "role")
sealed interface Voter

@JsonTypeName("genesisDelegate")
data class GenesisDelegateVoter(
    @param:JsonProperty(value = "id", required = true)
    @get:JsonProperty("id")
    val id: String,
) : Voter

@JsonTypeName("constitutionalCommittee")
data class ConstitutionalCommitteeVoter(
    @param:JsonProperty(value = "id", required = true)
    @get:JsonProperty("id")
    val id: String,
) : Voter

@JsonTypeName("delegateRepresentative")
data class DelegateRepresentativeVoter(
    @param:JsonProperty(value = "id", required = true)
    @get:JsonProperty("id")
    val id: String,
) : Voter

@JsonTypeName("stakePoolOperator")
data class StakePoolOperatorVoter(
    @param:JsonProperty(value = "id", required = true)
    @get:JsonProperty("id")
    val id: String,
) : Voter
