package io.projectnewm.kogmios.protocols.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.math.BigDecimal

@Serializable
data class ProtocolParameters(
    @SerialName("minFeeCoefficient")
    val minFeeCoefficient: Int,
    @SerialName("minFeeConstant")
    val minFeeConstant: Int,
    @SerialName("maxBlockBodySize")
    val maxBlockBodySize: Int,
    @SerialName("maxBlockHeaderSize")
    val maxBlockHeaderSize: Int,
    @SerialName("maxTxSize")
    val maxTxSize: Int,
    @SerialName("stakeKeyDeposit")
    val stakeKeyDeposit: Long,
    @SerialName("poolDeposit")
    val poolDeposit: Long,
    @SerialName("poolRetirementEpochBound")
    val poolRetirementEpochBound: Int,
    @SerialName("desiredNumberOfPools")
    val desiredNumberOfPools: Int,
    @SerialName("poolInfluence")
    @Contextual
    val poolInfluence: BigDecimal,
    @SerialName("monetaryExpansion")
    @Contextual
    val monetaryExpansion: BigDecimal,
    @SerialName("treasuryExpansion")
    @Contextual
    val treasuryExpansion: BigDecimal,
    @SerialName("decentralizationParameter")
    @Contextual
    val decentralizationParameter: BigDecimal,
    @SerialName("extraEntropy")
    val extraEntropy: String,
    @SerialName("protocolVersion")
    val protocolVersion: ProtocolVersion,
    @SerialName("minUtxoValue")
    val minUtxoValue: Long,
    @SerialName("minPoolCost")
    val minPoolCost: Long
)
