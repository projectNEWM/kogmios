package io.newm.kogmios.protocols.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.apache.commons.numbers.fraction.BigFraction

@Serializable
data class ShelleyGenesisProtocolParameters(
    @SerialName("minFeeCoefficient")
    val minFeeCoefficient: Int,
    @SerialName("minFeeConstant")
    val minFeeConstant: Ada,
    @SerialName("maxBlockBodySize")
    val maxBlockBodySize: BytesSize,
    @SerialName("maxBlockHeaderSize")
    val maxBlockHeaderSize: BytesSize,
    @SerialName("maxTransactionSize")
    val maxTransactionSize: BytesSize,
    @SerialName("stakeCredentialDeposit")
    val stakeCredentialDeposit: Ada,
    @SerialName("stakePoolDeposit")
    val stakePoolDeposit: Ada,
    @SerialName("stakePoolRetirementEpochBound")
    val stakePoolRetirementEpochBound: Int,
    @SerialName("desiredNumberOfStakePools")
    val desiredNumberOfStakePools: Int,
    @SerialName("stakePoolPledgeInfluence")
    @Contextual
    val stakePoolPledgeInfluence: BigFraction,
    @SerialName("monetaryExpansion")
    @Contextual
    val monetaryExpansion: BigFraction,
    @SerialName("treasuryExpansion")
    @Contextual
    val treasuryExpansion: BigFraction,
    @SerialName("federatedBlockProductionRatio")
    @Contextual
    val federatedBlockProductionRatio: BigFraction,
    @SerialName("extraEntropy")
    val extraEntropy: String,
    @SerialName("minUtxoDepositConstant")
    val minUtxoDepositConstant: Ada,
    @SerialName("minUtxoDepositCoefficient")
    val minUtxoDepositCoefficient: Int,
    @SerialName("version")
    val version: Version,
)
