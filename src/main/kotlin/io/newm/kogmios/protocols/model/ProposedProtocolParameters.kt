package io.newm.kogmios.protocols.model

import java.math.BigInteger

import org.apache.commons.numbers.fraction.BigFraction

data class ProposedProtocolParameters(
    val minFeeCoefficient: BigInteger? = null,
    val minFeeConstant: Ada? = null,
    val minFeeReferenceScripts: MinFeeReferenceScripts? = null,
    val minUtxoDepositCoefficient: BigInteger? = null,
    val minUtxoDepositConstant: Ada? = null,
    val maxBlockBodySize: BytesSize? = null,
    val maxBlockHeaderSize: BytesSize? = null,
    val maxTransactionSize: BytesSize? = null,
    val maxReferenceScriptsSize: BytesSize? = null,
    val maxValueSize: BytesSize? = null,
    val extraEntropy: String? = null,
    val stakeCredentialDeposit: Ada? = null,
    val stakePoolDeposit: Ada? = null,
    val stakePoolRetirementEpochBound: BigInteger? = null,
    val stakePoolPledgeInfluence: BigFraction? = null,
    val minStakePoolCost: Ada? = null,
    val desiredNumberOfStakePools: BigInteger? = null,
    val federatedBlockProductionRatio: BigFraction? = null,
    val monetaryExpansion: BigFraction? = null,
    val treasuryExpansion: BigFraction? = null,
    val collateralPercentage: BigInteger? = null,
    val maxCollateralInputs: BigInteger? = null,
    val plutusCostModels: PlutusCostModels? = null,
    val scriptExecutionPrices: ExecutionPrices? = null,
    val maxExecutionUnitsPerTransaction: ExecutionUnits? = null,
    val maxExecutionUnitsPerBlock: ExecutionUnits? = null,
    val stakePoolVotingThresholds: StakePoolVotingThresholds? = null,
    val constitutionalCommitteeMinSize: BigInteger? = null,
    val constitutionalCommitteeMaxTermLength: BigInteger? = null,
    val governanceActionLifetime: BigInteger? = null,
    val governanceActionDeposit: Ada? = null,
    val delegateRepresentativeVotingThresholds: DelegateRepresentativeVotingThresholds? = null,
    val delegateRepresentativeDeposit: Ada? = null,
    val delegateRepresentativeMaxIdleTime: BigInteger? = null,
    val version: Version? = null,
)
