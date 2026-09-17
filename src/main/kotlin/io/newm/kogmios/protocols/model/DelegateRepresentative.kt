package io.newm.kogmios.protocols.model

import com.fasterxml.jackson.annotation.JsonProperty

import com.fasterxml.jackson.annotation.JsonTypeInfo

import com.fasterxml.jackson.annotation.JsonTypeName

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
sealed interface DelegateRepresentative

@JsonTypeName("registered")
data class DelegateRepresentativeRegistered(
    @param:JsonProperty(value = "id", required = true)
    @get:JsonProperty("id")
    val id: String,
    @param:JsonProperty(value = "from", required = true)
    @get:JsonProperty("from")
    val from: String,
) : DelegateRepresentative

@JsonTypeName("noConfidence")
data object DelegateRepresentativeNoConfidence : DelegateRepresentative

@JsonTypeName("abstain")
data object DelegateRepresentativeAbstain : DelegateRepresentative
