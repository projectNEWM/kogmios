package io.newm.kogmios.protocols.model

import kotlinx.serialization.Serializable

@Serializable
data class CompactGenesisAlonzo(
    val coinsPerUtxoWord: Long,
    val collateralPercentage: Long,
    val costModels: CostModels,
    val prices: ExecutionUnits,
    val maxExecutionUnitsPerTransaction: ExecutionUnits,
    val maxExecutionUnitsPerBlock: ExecutionUnits,
    val maxValueSize: Long?,
    val maxCollateralInputs: Long?,
) : QueryResult
