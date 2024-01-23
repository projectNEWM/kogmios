package io.newm.kogmios.protocols.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.apache.commons.numbers.fraction.BigFraction

@Serializable
data class PoolResult(
    @SerialName("id")
    val id: String,
    @SerialName("vrfVerificationKeyHash")
    val vrfVerificationKeyHash: String,
    @SerialName("pledge")
    val pledge: Ada,
    @SerialName("cost")
    val cost: Ada,
    @Contextual
    @SerialName("margin")
    val margin: BigFraction,
    @SerialName("rewardAccount")
    val rewardAccount: String,
    @SerialName("owners")
    val owners: List<String>,
    @SerialName("relays")
    val relays: List<RelayResult>?,
    @SerialName("metadata")
    val metadata: MetadataResult,
)
