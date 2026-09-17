package io.newm.kogmios.protocols.model

import com.fasterxml.jackson.annotation.JsonProperty

import org.apache.commons.numbers.fraction.BigFraction

data class ShelleyGenesisProtocolParameters(
    @param:JsonProperty(value = "minFeeCoefficient", required = true)
    @get:JsonProperty("minFeeCoefficient")
    val minFeeCoefficient: Int,
    @param:JsonProperty(value = "minFeeConstant", required = true)
    @get:JsonProperty("minFeeConstant")
    val minFeeConstant: Ada,
    @param:JsonProperty(value = "maxBlockBodySize", required = true)
    @get:JsonProperty("maxBlockBodySize")
    val maxBlockBodySize: BytesSize,
    @param:JsonProperty(value = "maxBlockHeaderSize", required = true)
    @get:JsonProperty("maxBlockHeaderSize")
    val maxBlockHeaderSize: BytesSize,
    @param:JsonProperty(value = "maxTransactionSize", required = true)
    @get:JsonProperty("maxTransactionSize")
    val maxTransactionSize: BytesSize,
    @param:JsonProperty(value = "stakeCredentialDeposit", required = true)
    @get:JsonProperty("stakeCredentialDeposit")
    val stakeCredentialDeposit: Ada,
    @param:JsonProperty(value = "stakePoolDeposit", required = true)
    @get:JsonProperty("stakePoolDeposit")
    val stakePoolDeposit: Ada,
    @param:JsonProperty(value = "stakePoolRetirementEpochBound", required = true)
    @get:JsonProperty("stakePoolRetirementEpochBound")
    val stakePoolRetirementEpochBound: Int,
    @param:JsonProperty(value = "desiredNumberOfStakePools", required = true)
    @get:JsonProperty("desiredNumberOfStakePools")
    val desiredNumberOfStakePools: Int,
    @param:JsonProperty(value = "stakePoolPledgeInfluence", required = true)
    @get:JsonProperty("stakePoolPledgeInfluence")
    val stakePoolPledgeInfluence: BigFraction,
    @param:JsonProperty(value = "monetaryExpansion", required = true)
    @get:JsonProperty("monetaryExpansion")
    val monetaryExpansion: BigFraction,
    @param:JsonProperty(value = "treasuryExpansion", required = true)
    @get:JsonProperty("treasuryExpansion")
    val treasuryExpansion: BigFraction,
    @param:JsonProperty(value = "federatedBlockProductionRatio", required = true)
    @get:JsonProperty("federatedBlockProductionRatio")
    val federatedBlockProductionRatio: BigFraction,
    @param:JsonProperty(value = "extraEntropy", required = true)
    @get:JsonProperty("extraEntropy")
    val extraEntropy: String,
    @param:JsonProperty(value = "minUtxoDepositConstant", required = true)
    @get:JsonProperty("minUtxoDepositConstant")
    val minUtxoDepositConstant: Ada,
    @param:JsonProperty(value = "minUtxoDepositCoefficient", required = true)
    @get:JsonProperty("minUtxoDepositCoefficient")
    val minUtxoDepositCoefficient: Int,
    @param:JsonProperty(value = "version", required = true)
    @get:JsonProperty("version")
    val version: Version,
)
