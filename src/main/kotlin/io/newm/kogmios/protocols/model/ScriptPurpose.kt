package io.newm.kogmios.protocols.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonClassDiscriminator

@Serializable
@JsonClassDiscriminator("purpose")
sealed interface ScriptPurpose

@Serializable
@SerialName("spend")
data class ScriptPurposeSpend(
    @SerialName("purpose")
    val purpose: String,
    @SerialName("outputReference")
    val outputReference: UtxoOutputReference,
) : ScriptPurpose

@Serializable
@SerialName("mint")
data class ScriptPurposeMint(
    @SerialName("purpose")
    val purpose: String,
    @SerialName("policy")
    val policy: String,
) : ScriptPurpose

@Serializable
@SerialName("publish")
data class ScriptPurposePublish(
    @SerialName("purpose")
    val purpose: String,
    @SerialName("certificate")
    val certificate: Certificate,
) : ScriptPurpose

@Serializable
@SerialName("withdraw")
data class ScriptPurposeWithdraw(
    @SerialName("purpose")
    val purpose: String,
    @SerialName("rewardAccount")
    val rewardAccount: String,
) : ScriptPurpose
