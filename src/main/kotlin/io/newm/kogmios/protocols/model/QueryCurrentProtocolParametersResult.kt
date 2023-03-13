package io.newm.kogmios.protocols.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject
import java.math.BigDecimal
import java.math.BigInteger

@Serializable(with = QueryCurrentProtocolParametersResultSerializer::class)
sealed class QueryCurrentProtocolParametersResult : QueryResult

@Serializable
data class QueryCurrentProtocolAlonzoParametersResult(
    @Contextual
    @SerialName("minFeeCoefficient")
    val minFeeCoefficient: BigInteger,
    @Contextual
    @SerialName("minFeeConstant")
    val minFeeConstant: BigInteger,
    @Contextual
    @SerialName("maxBlockBodySize")
    val maxBlockBodySize: BigInteger,
    @Contextual
    @SerialName("maxBlockHeaderSize")
    val maxBlockHeaderSize: BigInteger,
    @Contextual
    @SerialName("maxTxSize")
    val maxTxSize: BigInteger,
    @Contextual
    @SerialName("stakeKeyDeposit")
    val stakeKeyDeposit: BigInteger,
    @Contextual
    @SerialName("poolDeposit")
    val poolDeposit: BigInteger,
    @Contextual
    @SerialName("poolRetirementEpochBound")
    val poolRetirementEpochBound: BigInteger,
    @Contextual
    @SerialName("desiredNumberOfPools")
    val desiredNumberOfPools: BigInteger,
    @SerialName("poolInfluence")
    @Contextual
    val poolInfluence: BigDecimal,
    @SerialName("monetaryExpansion")
    @Contextual
    val monetaryExpansion: BigDecimal,
    @SerialName("treasuryExpansion")
    @Contextual
    val treasuryExpansion: BigDecimal,
    @Contextual
    @SerialName("decentralizationParameter")
    val decentralizationParameter: BigDecimal,
    @SerialName("extraEntropy")
    val extraEntropy: String,
    @SerialName("protocolVersion")
    val protocolVersion: ProtocolVersion,
    @Contextual
    @SerialName("minPoolCost")
    val minPoolCost: BigInteger,
    @Contextual
    @SerialName("coinsPerUtxoWord")
    val coinsPerUtxoWord: BigInteger,
    @SerialName("costModels")
    val costModels: CostModels,
    @SerialName("prices")
    val prices: ExecutionUnits,
    @SerialName("maxExecutionUnitsPerTransaction")
    val maxExecutionUnitsPerTransaction: ExecutionUnits,
    @SerialName("maxExecutionUnitsPerBlock")
    val maxExecutionUnitsPerBlock: ExecutionUnits,
    @Contextual
    @SerialName("maxValueSize")
    val maxValueSize: BigInteger,
    @Contextual
    @SerialName("collateralPercentage")
    val collateralPercentage: BigInteger,
    @Contextual
    @SerialName("maxCollateralInputs")
    val maxCollateralInputs: BigInteger,
) : QueryCurrentProtocolParametersResult(), QueryResult

@Serializable
data class QueryCurrentProtocolBabbageParametersResult(
    @Contextual
    @SerialName("minFeeCoefficient")
    val minFeeCoefficient: BigInteger,
    @Contextual
    @SerialName("minFeeConstant")
    val minFeeConstant: BigInteger,
    @Contextual
    @SerialName("maxBlockBodySize")
    val maxBlockBodySize: BigInteger,
    @Contextual
    @SerialName("maxBlockHeaderSize")
    val maxBlockHeaderSize: BigInteger,
    @Contextual
    @SerialName("maxTxSize")
    val maxTxSize: BigInteger,
    @Contextual
    @SerialName("stakeKeyDeposit")
    val stakeKeyDeposit: BigInteger,
    @Contextual
    @SerialName("poolDeposit")
    val poolDeposit: BigInteger,
    @Contextual
    @SerialName("poolRetirementEpochBound")
    val poolRetirementEpochBound: BigInteger,
    @Contextual
    @SerialName("desiredNumberOfPools")
    val desiredNumberOfPools: BigInteger,
    @SerialName("poolInfluence")
    @Contextual
    val poolInfluence: BigDecimal,
    @SerialName("monetaryExpansion")
    @Contextual
    val monetaryExpansion: BigDecimal,
    @SerialName("treasuryExpansion")
    @Contextual
    val treasuryExpansion: BigDecimal,
    @SerialName("protocolVersion")
    val protocolVersion: ProtocolVersion,
    @Contextual
    @SerialName("minPoolCost")
    val minPoolCost: BigInteger,
    @Contextual
    @SerialName("coinsPerUtxoByte")
    val coinsPerUtxoByte: BigInteger,
    @SerialName("costModels")
    val costModels: CostModels,
    @SerialName("prices")
    val prices: ExecutionUnits,
    @SerialName("maxExecutionUnitsPerTransaction")
    val maxExecutionUnitsPerTransaction: ExecutionUnits,
    @SerialName("maxExecutionUnitsPerBlock")
    val maxExecutionUnitsPerBlock: ExecutionUnits,
    @Contextual
    @SerialName("maxValueSize")
    val maxValueSize: BigInteger,
    @Contextual
    @SerialName("collateralPercentage")
    val collateralPercentage: BigInteger,
    @Contextual
    @SerialName("maxCollateralInputs")
    val maxCollateralInputs: BigInteger,
) : QueryCurrentProtocolParametersResult(), QueryResult

object QueryCurrentProtocolParametersResultSerializer :
    JsonContentPolymorphicSerializer<QueryCurrentProtocolParametersResult>(QueryCurrentProtocolParametersResult::class) {
    override fun selectDeserializer(element: JsonElement): DeserializationStrategy<QueryCurrentProtocolParametersResult> {
        require(element is JsonObject)
        return if ("decentralizationParameter" in element.jsonObject) {
            QueryCurrentProtocolAlonzoParametersResult.serializer()
        } else {
            QueryCurrentProtocolBabbageParametersResult.serializer()
        }
    }
}
