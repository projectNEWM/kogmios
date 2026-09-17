package io.newm.kogmios.protocols.model

import com.fasterxml.jackson.annotation.JsonProperty

import org.apache.commons.numbers.fraction.BigFraction

data class PoolResult(
    @param:JsonProperty(value = "id", required = true)
    @get:JsonProperty("id")
    val id: String,
    @param:JsonProperty(value = "vrfVerificationKeyHash", required = true)
    @get:JsonProperty("vrfVerificationKeyHash")
    val vrfVerificationKeyHash: String,
    @param:JsonProperty(value = "pledge", required = true)
    @get:JsonProperty("pledge")
    val pledge: Ada,
    @param:JsonProperty(value = "cost", required = true)
    @get:JsonProperty("cost")
    val cost: Ada,
    @param:JsonProperty(value = "margin", required = true)
    @get:JsonProperty("margin")
    val margin: BigFraction,
    @param:JsonProperty(value = "rewardAccount", required = true)
    @get:JsonProperty("rewardAccount")
    val rewardAccount: String,
    @param:JsonProperty(value = "owners", required = true)
    @get:JsonProperty("owners")
    val owners: List<String>,
    @param:JsonProperty(value = "relays", required = true)
    @get:JsonProperty("relays")
    val relays: List<RelayResult>?,
    @param:JsonProperty(value = "metadata", required = true)
    @get:JsonProperty("metadata")
    val metadata: MetadataResult,
)
