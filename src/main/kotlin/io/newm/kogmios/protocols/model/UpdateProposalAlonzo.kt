package io.newm.kogmios.protocols.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.math.BigDecimal
import java.math.BigInteger

@Serializable
data class UpdateProposalAlonzo(
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
    @SerialName("decentralizationParameter")
    @Contextual
    val decentralizationParameter: BigDecimal?,
    @SerialName("coinsPerUtxoWord")
    @Contextual
    val coinsPerUtxoWord: BigInteger?,
    @SerialName("minPoolCost")
    @Contextual
    val minPoolCost: BigInteger?,
    @SerialName("extraEntropy")
    val extraEntropy: String?,
    @SerialName("protocolVersion")
    val protocolVersion: ProtocolVersion?,
    @SerialName("costModels")
    val costModels: CostModels?,
    @SerialName("prices")
    val prices: Prices?,
    @SerialName("maxExecutionUnitsPerTransaction")
    val maxExecutionUnitsPerTransaction: Prices?,
    @SerialName("maxExecutionUnitsPerBlock")
    val maxExecutionUnitsPerBlock: Prices?,
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