package io.newm.kogmios.protocols.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.apache.commons.numbers.fraction.BigFraction
import java.math.BigInteger

@Serializable
data class UpdatableParameters(
    @SerialName("scriptVersion")
    val scriptVersion: Int,
    @SerialName("slotDuration")
    val slotDuration: Long,
    @SerialName("maxBlockBodySize")
    val maxBlockBodySize: BytesSize,
    @SerialName("maxBlockHeaderSize")
    val maxBlockHeaderSize: BytesSize,
    @SerialName("maxTransactionSize")
    val maxTransactionSize: BytesSize,
    @SerialName("maxUpdateProposalSize")
    val maxUpdateProposalSize: BytesSize,
    @SerialName("multiPartyComputationThreshold")
    @Contextual
    val multiPartyComputationThreshold: BigFraction,
    @SerialName("heavyDelegationThreshold")
    @Contextual
    val heavyDelegationThreshold: BigFraction,
    @SerialName("updateVoteThreshold")
    @Contextual
    val updateVoteThreshold: BigFraction,
    @SerialName("updateProposalThreshold")
    @Contextual
    val updateProposalThreshold: BigFraction,
    @SerialName("updateProposalTimeToLive")
    val updateProposalTimeToLive: Long,
    @SerialName("unlockStakeEpoch")
    @Contextual
    val unlockStakeEpoch: BigInteger,
    @SerialName("softForkInitThreshold")
    @Contextual
    val softForkInitThreshold: BigFraction,
    @SerialName("softForkMinThreshold")
    @Contextual
    val softForkMinThreshold: BigFraction,
    @SerialName("softForkDecrementThreshold")
    @Contextual
    val softForkDecrementThreshold: BigFraction,
    @SerialName("minFeeConstant")
    val minFeeConstant: Ada,
    @SerialName("minFeeCoefficient")
    val minFeeCoefficient: Int,
)
