package io.newm.kogmios.protocols.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.apache.commons.numbers.fraction.BigFraction

@Serializable
data class UpdatableParametersProposal(
    @SerialName("scriptVersion")
    val scriptVersion: Int? = null,
    @SerialName("slotDuration")
    val slotDuration: Long? = null,
    @SerialName("maxBlockBodySize")
    val maxBlockBodySize: BytesSize? = null,
    @SerialName("maxBlockHeaderSize")
    val maxBlockHeaderSize: BytesSize? = null,
    @SerialName("maxTransactionSize")
    val maxTransactionSize: BytesSize? = null,
    @SerialName("maxUpdateProposalSize")
    val maxUpdateProposalSize: BytesSize? = null,
    @SerialName("multiPartyComputationThreshold")
    @Contextual
    val multiPartyComputationThreshold: BigFraction? = null,
    @SerialName("heavyDelegationThreshold")
    @Contextual
    val heavyDelegationThreshold: BigFraction? = null,
    @SerialName("updateVoteThreshold")
    @Contextual
    val updateVoteThreshold: BigFraction? = null,
    @SerialName("updateProposalThreshold")
    @Contextual
    val updateProposalThreshold: BigFraction? = null,
    @SerialName("updateProposalTimeToLive")
    val updateProposalTimeToLive: Long? = null,
    @SerialName("unlockStakeEpoch")
    val unlockStakeEpoch: Long? = null,
    @SerialName("softForkInitThreshold")
    @Contextual
    val softForkInitThreshold: BigFraction? = null,
    @SerialName("softForkMinThreshold")
    @Contextual
    val softForkMinThreshold: BigFraction? = null,
    @SerialName("softForkDecrementThreshold")
    @Contextual
    val softForkDecrementThreshold: BigFraction? = null,
    @SerialName("minFeeConstant")
    val minFeeConstant: Ada? = null,
    @SerialName("minFeeCoefficient")
    val minFeeCoefficient: Int? = null,
)
