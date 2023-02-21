package io.newm.kogmios.protocols.model

import kotlinx.datetime.Instant
import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.math.BigDecimal
import java.math.BigInteger

@Serializable
data class CompactGenesisByron(
    @SerialName("genesisKeyHashes")
    val genesisKeyHashes: List<String>,
    @SerialName("genesisDelegations")
    val genesisDelegations: Map<String, ByronGenesisDelegation>,
    @SerialName("systemStart")
    val systemStart: Instant,
    @SerialName("initialFunds")
    val initialFunds: Map<String, @Contextual BigInteger>,
    @SerialName("initialCoinOffering")
    val initialCoinOffering: Map<String, @Contextual BigInteger>,
    @SerialName("securityParameter")
    @Contextual
    val securityParameter: BigInteger,
    @SerialName("networkMagic")
    val networkMagic: Long,
    @SerialName("protocolParameters")
    val protocolParameters: ProtocolParametersByron,
) : QueryResult

@Serializable
data class ByronGenesisDelegation(
    @SerialName("epoch")
    val epoch: Long,
    @SerialName("issuerVk")
    val issuerVk: String,
    @SerialName("delegateVk")
    val delegateVk: String,
    @SerialName("signature")
    val signature: String,
)

@Serializable
data class ProtocolParametersByron(
    @SerialName("scriptVersion")
    val scriptVersion: Long?,
    @SerialName("slotDuration")
    val slotDuration: Long?,
    @SerialName("maxBlockSize")
    val maxBlockSize: Long?,
    @SerialName("maxHeaderSize")
    val maxHeaderSize: Long?,
    @SerialName("maxTxSize")
    val maxTxSize: Long?,
    @SerialName("maxProposalSize")
    val maxProposalSize: Long?,
    @SerialName("mpcThreshold")
    @Contextual
    val mpcThreshold: BigDecimal?,
    @SerialName("heavyDlgThreshold")
    @Contextual
    val heavyDlgThreshold: BigDecimal?,
    @SerialName("updateVoteThreshold")
    @Contextual
    val updateVoteThreshold: BigDecimal?,
    @SerialName("updateProposalThreshold")
    @Contextual
    val updateProposalThreshold: BigDecimal?,
    @SerialName("updateProposalTimeToLive")
    val updateProposalTimeToLive: Long?,
    @SerialName("softforkRule")
    val softForkRule: SoftForkRule?,
    @SerialName("txFeePolicy")
    val txFeePolicy: TxFeePolicy?,
    @SerialName("unlockStakeEpoch")
    @Contextual
    val unlockStakeEpoch: BigInteger?,
)

@Serializable
data class SoftForkRule(
    @SerialName("initThreshold")
    @Contextual
    val initThreshold: BigDecimal?,
    @SerialName("minThreshold")
    @Contextual
    val minThreshold: BigDecimal?,
    @SerialName("decrementThreshold")
    @Contextual
    val decrementThreshold: BigDecimal?,
)

@Serializable
data class TxFeePolicy(
    @SerialName("coefficient")
    @Contextual
    val coefficient: BigDecimal,
    @SerialName("constant")
    @Contextual
    val constant: BigInteger,
)
