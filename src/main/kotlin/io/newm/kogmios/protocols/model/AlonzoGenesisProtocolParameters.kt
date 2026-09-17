package io.newm.kogmios.protocols.model

import com.fasterxml.jackson.annotation.JsonProperty

data class AlonzoGenesisProtocolParameters(
    @param:JsonProperty(value = "minUtxoDepositCoefficient", required = true)
    @get:JsonProperty("minUtxoDepositCoefficient")
    val minUtxoDepositCoefficient: Long,
    @param:JsonProperty(value = "collateralPercentage", required = true)
    @get:JsonProperty("collateralPercentage")
    val collateralPercentage: Long,
    @param:JsonProperty(value = "plutusCostModels", required = true)
    @get:JsonProperty("plutusCostModels")
    val plutusCostModels: PlutusCostModels,
    @param:JsonProperty(value = "maxCollateralInputs", required = true)
    @get:JsonProperty("maxCollateralInputs")
    val maxCollateralInputs: Long,
    @param:JsonProperty(value = "maxExecutionUnitsPerBlock", required = true)
    @get:JsonProperty("maxExecutionUnitsPerBlock")
    val maxExecutionUnitsPerBlock: ExecutionUnits,
    @param:JsonProperty(value = "maxExecutionUnitsPerTransaction", required = true)
    @get:JsonProperty("maxExecutionUnitsPerTransaction")
    val maxExecutionUnitsPerTransaction: ExecutionUnits,
    @param:JsonProperty(value = "maxValueSize", required = true)
    @get:JsonProperty("maxValueSize")
    val maxValueSize: BytesSize,
    @param:JsonProperty(value = "scriptExecutionPrices", required = true)
    @get:JsonProperty("scriptExecutionPrices")
    val scriptExecutionPrices: ExecutionPrices,
)
