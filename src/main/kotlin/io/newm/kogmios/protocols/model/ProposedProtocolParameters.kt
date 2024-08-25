package io.newm.kogmios.protocols.model

import java.math.BigInteger
import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.apache.commons.numbers.fraction.BigFraction

@Serializable
data class ProposedProtocolParameters(
    @Contextual
    @SerialName("minFeeCoefficient")
    val minFeeCoefficient: BigInteger? = null,
    @SerialName("minFeeConstant")
    val minFeeConstant: Ada? = null,
    @SerialName("minFeeReferenceScripts")
    val minFeeReferenceScripts: MinFeeReferenceScripts? = null,
    @Contextual
    @SerialName("minUtxoDepositCoefficient")
    val minUtxoDepositCoefficient: BigInteger? = null,
    @SerialName("minUtxoDepositConstant")
    val minUtxoDepositConstant: Ada? = null,
    @SerialName("maxBlockBodySize")
    val maxBlockBodySize: BytesSize? = null,
    @SerialName("maxBlockHeaderSize")
    val maxBlockHeaderSize: BytesSize? = null,
    @SerialName("maxTransactionSize")
    val maxTransactionSize: BytesSize? = null,
    @SerialName("maxReferenceScriptsSize")
    val maxReferenceScriptsSize: BytesSize? = null,
    @SerialName("maxValueSize")
    val maxValueSize: BytesSize? = null,
    @SerialName("extraEntropy")
    val extraEntropy: String? = null,
    @SerialName("stakeCredentialDeposit")
    val stakeCredentialDeposit: Ada? = null,
    @SerialName("stakePoolDeposit")
    val stakePoolDeposit: Ada? = null,
    @Contextual
    @SerialName("stakePoolRetirementEpochBound")
    val stakePoolRetirementEpochBound: BigInteger? = null,
    @Contextual
    @SerialName("stakePoolPledgeInfluence")
    val stakePoolPledgeInfluence: BigFraction? = null,
    @SerialName("minStakePoolCost")
    val minStakePoolCost: Ada? = null,
    @Contextual
    @SerialName("desiredNumberOfStakePools")
    val desiredNumberOfStakePools: BigInteger? = null,
    @Contextual
    @SerialName("federatedBlockProductionRatio")
    val federatedBlockProductionRatio: BigFraction? = null,
    @Contextual
    @SerialName("monetaryExpansion")
    val monetaryExpansion: BigFraction? = null,
    @Contextual
    @SerialName("treasuryExpansion")
    val treasuryExpansion: BigFraction? = null,
    @Contextual
    @SerialName("collateralPercentage")
    val collateralPercentage: BigInteger? = null,
    @Contextual
    @SerialName("maxCollateralInputs")
    val maxCollateralInputs: BigInteger? = null,
    @SerialName("plutusCostModels")
    val plutusCostModels: PlutusCostModels? = null,
    @SerialName("scriptExecutionPrices")
    val scriptExecutionPrices: ExecutionPrices? = null,
    @SerialName("maxExecutionUnitsPerTransaction")
    val maxExecutionUnitsPerTransaction: ExecutionUnits? = null,
    @SerialName("maxExecutionUnitsPerBlock")
    val maxExecutionUnitsPerBlock: ExecutionUnits? = null,
    @SerialName("stakePoolVotingThresholds")
    val stakePoolVotingThresholds: StakePoolVotingThresholds? = null,
    @SerialName("constitutionalCommitteeMinSize")
    @Contextual
    val constitutionalCommitteeMinSize: BigInteger? = null,
    @SerialName("constitutionalCommitteeMaxTermLength")
    @Contextual
    val constitutionalCommitteeMaxTermLength: BigInteger? = null,
    @SerialName("governanceActionLifetime")
    @Contextual
    val governanceActionLifetime: BigInteger? = null,
    @SerialName("governanceActionDeposit")
    val governanceActionDeposit: Ada? = null,
    @SerialName("delegateRepresentativeVotingThresholds")
    val delegateRepresentativeVotingThresholds: DelegateRepresentativeVotingThresholds? = null,
    @SerialName("delegateRepresentativeDeposit")
    val delegateRepresentativeDeposit: Ada? = null,
    @SerialName("delegateRepresentativeMaxIdleTime")
    @Contextual
    val delegateRepresentativeMaxIdleTime: BigInteger? = null,
    @SerialName("version")
    val version: Version? = null,
)
