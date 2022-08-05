package io.newm.kogmios.protocols.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.math.BigDecimal
import java.math.BigInteger

// decentralizationParameter, minUtxoValue, extraEntropy
@Serializable
data class UpdateProposalBabbage(
    @SerialName("minFeeCoefficient")
    @Contextual
    val minFeeCoefficient: BigInteger?,
    @SerialName("minFeeConstant")
    @Contextual
    val minFeeConstant: BigInteger?,
    @SerialName("maxBlockBodySize")
    @Contextual
    val maxBlockBodySize: BigInteger?,
    @SerialName("maxBlockHeaderSize")
    @Contextual
    val maxBlockHeaderSize: BigInteger?,
    @SerialName("maxTxSize")
    @Contextual
    val maxTxSize: BigInteger?,
    @SerialName("stakeKeyDeposit")
    @Contextual
    val stakeKeyDeposit: BigInteger?,
    @SerialName("poolDeposit")
    @Contextual
    val poolDeposit: BigInteger?,
    @SerialName("poolRetirementEpochBound")
    @Contextual
    val poolRetirementEpochBound: BigInteger?,
    @SerialName("desiredNumberOfPools")
    @Contextual
    val desiredNumberOfPools: BigInteger?,
    @SerialName("poolInfluence")
    @Contextual
    val poolInfluence: BigDecimal?,
    @SerialName("monetaryExpansion")
    @Contextual
    val monetaryExpansion: BigDecimal?,
    @SerialName("treasuryExpansion")
    @Contextual
    val treasuryExpansion: BigDecimal?,
    @SerialName("coinsPerUtxoByte")
    @Contextual
    val coinsPerUtxoByte: BigInteger?,
    @SerialName("minPoolCost")
    @Contextual
    val minPoolCost: BigInteger?,
    @SerialName("protocolVersion")
    val protocolVersion: ProtocolVersion?,
    @SerialName("costModels")
    val costModels: CostModels?,
    @SerialName("prices")
    val prices: ExecutionUnits?,
    @SerialName("maxExecutionUnitsPerTransaction")
    val maxExecutionUnitsPerTransaction: ExecutionUnits?,
    @SerialName("maxExecutionUnitsPerBlock")
    val maxExecutionUnitsPerBlock: ExecutionUnits?,
    @SerialName("maxValueSize")
    @Contextual
    val maxValueSize: BigInteger?,
    @SerialName("collateralPercentage")
    @Contextual
    val collateralPercentage: BigInteger?,
    @SerialName("maxCollateralInputs")
    @Contextual
    val maxCollateralInputs: BigInteger?,
) : UpdateProposal()
