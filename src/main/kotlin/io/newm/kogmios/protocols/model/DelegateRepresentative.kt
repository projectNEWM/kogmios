package io.newm.kogmios.protocols.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonClassDiscriminator

@Serializable
@JsonClassDiscriminator("type")
sealed interface DelegateRepresentative

@Serializable
@SerialName("registered")
data class DelegateRepresentativeRegistered(
    @SerialName("id")
    val id: String,
) : DelegateRepresentative

@Serializable
@SerialName("noConfidence")
data object DelegateRepresentativeNoConfidence : DelegateRepresentative

@Serializable
@SerialName("abstain")
data object DelegateRepresentativeAbstain : DelegateRepresentative
