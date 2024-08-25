package io.newm.kogmios.protocols.model.result

import io.newm.kogmios.protocols.model.Ada
import io.newm.kogmios.protocols.model.BytesSize
import io.newm.kogmios.protocols.model.ExecutionPrices
import io.newm.kogmios.protocols.model.ExecutionUnits
import io.newm.kogmios.protocols.model.MinFeeReferenceScripts
import io.newm.kogmios.protocols.model.PlutusCostModels
import java.math.BigInteger
import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.apache.commons.numbers.fraction.BigFraction

@Serializable
data class ProtocolParametersResult(
    @Contextual
    @SerialName("minFeeCoefficient")
    val minFeeCoefficient: BigInteger,
    @SerialName("minFeeConstant")
    val minFeeConstant: Ada,
    @SerialName("minFeeReferenceScripts")
    val minFeeReferenceScripts: MinFeeReferenceScripts? = null,
    @Contextual
    @SerialName("minUtxoDepositCoefficient")
    val minUtxoDepositCoefficient: BigInteger,
    @Contextual
    @SerialName("minUtxoDepositConstant")
    val minUtxoDepositConstant: Ada,
    @SerialName("maxBlockBodySize")
    val maxBlockBodySize: BytesSize,
    @SerialName("maxBlockHeaderSize")
    val maxBlockHeaderSize: BytesSize,
    @SerialName("maxTransactionSize")
    val maxTransactionSize: BytesSize,
    @SerialName("maxReferenceScriptsSize")
    val maxReferenceScriptsSize: BytesSize? = null,
    @SerialName("maxValueSize")
    val maxValueSize: BytesSize,
    @SerialName("extraEntropy")
    val extraEntropy: String? = null,
    @SerialName("stakeCredentialDeposit")
    val stakeCredentialDeposit: Ada,
    @SerialName("stakePoolDeposit")
    val stakePoolDeposit: Ada,
    @Contextual
    @SerialName("stakePoolRetirementEpochBound")
    val stakePoolRetirementEpochBound: BigInteger,
    @Contextual
    @SerialName("stakePoolPledgeInfluence")
    val stakePoolPledgeInfluence: BigFraction,
    @SerialName("minStakePoolCost")
    val minStakePoolCost: Ada,
    @Contextual
    @SerialName("desiredNumberOfStakePools")
    val desiredNumberOfStakePools: BigInteger,
    @Contextual
    @SerialName("federatedBlockProductionRatio")
    val federatedBlockProductionRatio: BigFraction? = null,
    @SerialName("monetaryExpansion")
    @Contextual
    val monetaryExpansion: BigFraction,
    @SerialName("treasuryExpansion")
    @Contextual
    val treasuryExpansion: BigFraction,
    @Contextual
    @SerialName("collateralPercentage")
    val collateralPercentage: BigInteger,
    @Contextual
    @SerialName("maxCollateralInputs")
    val maxCollateralInputs: BigInteger,
    @SerialName("plutusCostModels")
    val plutusCostModels: PlutusCostModels,
    @SerialName("scriptExecutionPrices")
    val scriptExecutionPrices: ExecutionPrices,
    @SerialName("maxExecutionUnitsPerTransaction")
    val maxExecutionUnitsPerTransaction: ExecutionUnits,
    @SerialName("maxExecutionUnitsPerBlock")
    val maxExecutionUnitsPerBlock: ExecutionUnits,
    @SerialName("version")
    val version: io.newm.kogmios.protocols.model.Version,
) : OgmiosResult
