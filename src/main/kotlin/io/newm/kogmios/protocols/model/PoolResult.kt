package io.newm.kogmios.protocols.model

import com.fasterxml.jackson.annotation.JsonProperty

import org.apache.commons.numbers.fraction.BigFraction

data class PoolResult(
    @param:JsonProperty(value = "id", required = true)
    @get:JsonProperty("id")
    val id: String,
    val vrfVerificationKeyHash: String? = null,
    val pledge: Ada? = null,
    val cost: Ada? = null,
    val stake: Ada? = null,
    val margin: BigFraction? = null,
    val rewardAccount: String? = null,
    val owners: List<String>? = null,
    val relays: List<RelayResult>? = null,
    val metadata: MetadataResult? = null,
)
