package io.newm.kogmios.protocols.model

import org.apache.commons.numbers.fraction.BigFraction

data class UpdatableParametersProposal(
    val scriptVersion: Int? = null,
    val slotDuration: Long? = null,
    val maxBlockBodySize: BytesSize? = null,
    val maxBlockHeaderSize: BytesSize? = null,
    val maxTransactionSize: BytesSize? = null,
    val maxUpdateProposalSize: BytesSize? = null,
    val multiPartyComputationThreshold: BigFraction? = null,
    val heavyDelegationThreshold: BigFraction? = null,
    val updateVoteThreshold: BigFraction? = null,
    val updateProposalThreshold: BigFraction? = null,
    val updateProposalTimeToLive: Long? = null,
    val unlockStakeEpoch: Long? = null,
    val softForkInitThreshold: BigFraction? = null,
    val softForkMinThreshold: BigFraction? = null,
    val softForkDecrementThreshold: BigFraction? = null,
    val minFeeConstant: Ada? = null,
    val minFeeCoefficient: Int? = null,
)
