package io.newm.kogmios.protocols.model

import com.fasterxml.jackson.annotation.JsonProperty

import com.fasterxml.jackson.annotation.JsonTypeInfo

import com.fasterxml.jackson.annotation.JsonTypeName

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.EXISTING_PROPERTY,
    property = "purpose",
    visible = true,
)
sealed interface ScriptPurpose

@JsonTypeName("spend")
data class ScriptPurposeSpend(
    @param:JsonProperty(value = "purpose", required = true)
    @get:JsonProperty("purpose")
    val purpose: String,
    @param:JsonProperty(value = "outputReference", required = true)
    @get:JsonProperty("outputReference")
    val outputReference: UtxoOutputReference,
) : ScriptPurpose

@JsonTypeName("mint")
data class ScriptPurposeMint(
    @param:JsonProperty(value = "purpose", required = true)
    @get:JsonProperty("purpose")
    val purpose: String,
    @param:JsonProperty(value = "policy", required = true)
    @get:JsonProperty("policy")
    val policy: String,
) : ScriptPurpose

@JsonTypeName("publish")
data class ScriptPurposePublish(
    @param:JsonProperty(value = "purpose", required = true)
    @get:JsonProperty("purpose")
    val purpose: String,
    @param:JsonProperty(value = "certificate", required = true)
    @get:JsonProperty("certificate")
    val certificate: Certificate,
) : ScriptPurpose

@JsonTypeName("withdraw")
data class ScriptPurposeWithdraw(
    @param:JsonProperty(value = "purpose", required = true)
    @get:JsonProperty("purpose")
    val purpose: String,
    @param:JsonProperty(value = "rewardAccount", required = true)
    @get:JsonProperty("rewardAccount")
    val rewardAccount: String,
) : ScriptPurpose
