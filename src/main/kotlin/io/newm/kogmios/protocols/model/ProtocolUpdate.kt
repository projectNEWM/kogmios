package io.newm.kogmios.protocols.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonObject
import java.math.BigDecimal
import java.math.BigInteger

@Serializable
data class ProtocolUpdate(
    @SerialName("epoch")
    val epoch: Long,

    @SerialName("proposal")
    val proposal: Map<String, UpdateProposal>,
)

@Serializable(with = UpdateProposalSerializer::class)
sealed class UpdateProposal

@Serializable
data class UpdateProposalShelley(
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
    @SerialName("minUtxoValue")
    @Contextual
    val minUtxoValue: BigInteger?,
    @SerialName("minPoolCost")
    @Contextual
    val minPoolCost: BigInteger?,
    @SerialName("extraEntropy")
    val extraEntropy: String?,
    @SerialName("protocolVersion")
    val protocolVersion: ProtocolVersion?,
) : UpdateProposal()

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

object UpdateProposalSerializer : JsonContentPolymorphicSerializer<UpdateProposal>(UpdateProposal::class) {
    override fun selectDeserializer(element: JsonElement): DeserializationStrategy<out UpdateProposal> {
        return if ("coinsPerUtxoByte" in element.jsonObject) {
            UpdateProposalBabbage.serializer()
        } else if ("coinsPerUtxoWord" in element.jsonObject) {
            UpdateProposalAlonzo.serializer()
        } else {
            UpdateProposalShelley.serializer()
        }
    }
}
