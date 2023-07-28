package io.newm.kogmios.protocols.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AlonzoGenesisProtocolParameters(
    @SerialName("minUtxoDepositCoefficient")
    val minUtxoDepositCoefficient: Long,
    @SerialName("collateralPercentage")
    val collateralPercentage: Long,
    @SerialName("plutusCostModels")
    val plutusCostModels: PlutusCostModels,
    @SerialName("maxCollateralInputs")
    val maxCollateralInputs: Long,
    @SerialName("maxExecutionUnitsPerBlock")
    val maxExecutionUnitsPerBlock: ExecutionUnits,
    @SerialName("maxExecutionUnitsPerTransaction")
    val maxExecutionUnitsPerTransaction: ExecutionUnits,
    @SerialName("maxValueSize")
    val maxValueSize: BytesSize,
    @SerialName("scriptExecutionPrices")
    val scriptExecutionPrices: ExecutionPrices,
)
