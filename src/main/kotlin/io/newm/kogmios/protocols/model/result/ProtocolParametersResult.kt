package io.newm.kogmios.protocols.model.result

import com.fasterxml.jackson.annotation.JsonProperty

import io.newm.kogmios.protocols.model.Ada
import io.newm.kogmios.protocols.model.BytesSize
import io.newm.kogmios.protocols.model.ExecutionPrices
import io.newm.kogmios.protocols.model.ExecutionUnits
import io.newm.kogmios.protocols.model.MinFeeReferenceScripts
import io.newm.kogmios.protocols.model.PlutusCostModels
import java.math.BigInteger

import org.apache.commons.numbers.fraction.BigFraction

data class ProtocolParametersResult(
    @param:JsonProperty(value = "minFeeCoefficient", required = true)
    @get:JsonProperty("minFeeCoefficient")
    val minFeeCoefficient: BigInteger,
    @param:JsonProperty(value = "minFeeConstant", required = true)
    @get:JsonProperty("minFeeConstant")
    val minFeeConstant: Ada,
    val minFeeReferenceScripts: MinFeeReferenceScripts? = null,
    @param:JsonProperty(value = "minUtxoDepositCoefficient", required = true)
    @get:JsonProperty("minUtxoDepositCoefficient")
    val minUtxoDepositCoefficient: BigInteger,
    @param:JsonProperty(value = "minUtxoDepositConstant", required = true)
    @get:JsonProperty("minUtxoDepositConstant")
    val minUtxoDepositConstant: Ada,
    @param:JsonProperty(value = "maxBlockBodySize", required = true)
    @get:JsonProperty("maxBlockBodySize")
    val maxBlockBodySize: BytesSize,
    @param:JsonProperty(value = "maxBlockHeaderSize", required = true)
    @get:JsonProperty("maxBlockHeaderSize")
    val maxBlockHeaderSize: BytesSize,
    @param:JsonProperty(value = "maxTransactionSize", required = true)
    @get:JsonProperty("maxTransactionSize")
    val maxTransactionSize: BytesSize,
    val maxReferenceScriptsSize: BytesSize? = null,
    @param:JsonProperty(value = "maxValueSize", required = true)
    @get:JsonProperty("maxValueSize")
    val maxValueSize: BytesSize,
    val extraEntropy: String? = null,
    @param:JsonProperty(value = "stakeCredentialDeposit", required = true)
    @get:JsonProperty("stakeCredentialDeposit")
    val stakeCredentialDeposit: Ada,
    @param:JsonProperty(value = "stakePoolDeposit", required = true)
    @get:JsonProperty("stakePoolDeposit")
    val stakePoolDeposit: Ada,
    @param:JsonProperty(value = "stakePoolRetirementEpochBound", required = true)
    @get:JsonProperty("stakePoolRetirementEpochBound")
    val stakePoolRetirementEpochBound: BigInteger,
    @param:JsonProperty(value = "stakePoolPledgeInfluence", required = true)
    @get:JsonProperty("stakePoolPledgeInfluence")
    val stakePoolPledgeInfluence: BigFraction,
    @param:JsonProperty(value = "minStakePoolCost", required = true)
    @get:JsonProperty("minStakePoolCost")
    val minStakePoolCost: Ada,
    @param:JsonProperty(value = "desiredNumberOfStakePools", required = true)
    @get:JsonProperty("desiredNumberOfStakePools")
    val desiredNumberOfStakePools: BigInteger,
    val federatedBlockProductionRatio: BigFraction? = null,
    @param:JsonProperty(value = "monetaryExpansion", required = true)
    @get:JsonProperty("monetaryExpansion")
    val monetaryExpansion: BigFraction,
    @param:JsonProperty(value = "treasuryExpansion", required = true)
    @get:JsonProperty("treasuryExpansion")
    val treasuryExpansion: BigFraction,
    @param:JsonProperty(value = "collateralPercentage", required = true)
    @get:JsonProperty("collateralPercentage")
    val collateralPercentage: BigInteger,
    @param:JsonProperty(value = "maxCollateralInputs", required = true)
    @get:JsonProperty("maxCollateralInputs")
    val maxCollateralInputs: BigInteger,
    @param:JsonProperty(value = "plutusCostModels", required = true)
    @get:JsonProperty("plutusCostModels")
    val plutusCostModels: PlutusCostModels,
    @param:JsonProperty(value = "scriptExecutionPrices", required = true)
    @get:JsonProperty("scriptExecutionPrices")
    val scriptExecutionPrices: ExecutionPrices,
    @param:JsonProperty(value = "maxExecutionUnitsPerTransaction", required = true)
    @get:JsonProperty("maxExecutionUnitsPerTransaction")
    val maxExecutionUnitsPerTransaction: ExecutionUnits,
    @param:JsonProperty(value = "maxExecutionUnitsPerBlock", required = true)
    @get:JsonProperty("maxExecutionUnitsPerBlock")
    val maxExecutionUnitsPerBlock: ExecutionUnits,
    @param:JsonProperty(value = "version", required = true)
    @get:JsonProperty("version")
    val version: io.newm.kogmios.protocols.model.Version,
) : OgmiosResult
