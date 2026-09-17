package io.newm.kogmios.protocols.model

import com.fasterxml.jackson.annotation.JsonProperty

import org.apache.commons.numbers.fraction.BigFraction
import java.math.BigInteger

data class UpdatableParameters(
    @param:JsonProperty(value = "scriptVersion", required = true)
    @get:JsonProperty("scriptVersion")
    val scriptVersion: Int,
    @param:JsonProperty(value = "slotDuration", required = true)
    @get:JsonProperty("slotDuration")
    val slotDuration: Long,
    @param:JsonProperty(value = "maxBlockBodySize", required = true)
    @get:JsonProperty("maxBlockBodySize")
    val maxBlockBodySize: BytesSize,
    @param:JsonProperty(value = "maxBlockHeaderSize", required = true)
    @get:JsonProperty("maxBlockHeaderSize")
    val maxBlockHeaderSize: BytesSize,
    @param:JsonProperty(value = "maxTransactionSize", required = true)
    @get:JsonProperty("maxTransactionSize")
    val maxTransactionSize: BytesSize,
    @param:JsonProperty(value = "maxUpdateProposalSize", required = true)
    @get:JsonProperty("maxUpdateProposalSize")
    val maxUpdateProposalSize: BytesSize,
    @param:JsonProperty(value = "multiPartyComputationThreshold", required = true)
    @get:JsonProperty("multiPartyComputationThreshold")
    val multiPartyComputationThreshold: BigFraction,
    @param:JsonProperty(value = "heavyDelegationThreshold", required = true)
    @get:JsonProperty("heavyDelegationThreshold")
    val heavyDelegationThreshold: BigFraction,
    @param:JsonProperty(value = "updateVoteThreshold", required = true)
    @get:JsonProperty("updateVoteThreshold")
    val updateVoteThreshold: BigFraction,
    @param:JsonProperty(value = "updateProposalThreshold", required = true)
    @get:JsonProperty("updateProposalThreshold")
    val updateProposalThreshold: BigFraction,
    @param:JsonProperty(value = "updateProposalTimeToLive", required = true)
    @get:JsonProperty("updateProposalTimeToLive")
    val updateProposalTimeToLive: Long,
    @param:JsonProperty(value = "unlockStakeEpoch", required = true)
    @get:JsonProperty("unlockStakeEpoch")
    val unlockStakeEpoch: BigInteger,
    @param:JsonProperty(value = "softForkInitThreshold", required = true)
    @get:JsonProperty("softForkInitThreshold")
    val softForkInitThreshold: BigFraction,
    @param:JsonProperty(value = "softForkMinThreshold", required = true)
    @get:JsonProperty("softForkMinThreshold")
    val softForkMinThreshold: BigFraction,
    @param:JsonProperty(value = "softForkDecrementThreshold", required = true)
    @get:JsonProperty("softForkDecrementThreshold")
    val softForkDecrementThreshold: BigFraction,
    @param:JsonProperty(value = "minFeeConstant", required = true)
    @get:JsonProperty("minFeeConstant")
    val minFeeConstant: Ada,
    @param:JsonProperty(value = "minFeeCoefficient", required = true)
    @get:JsonProperty("minFeeCoefficient")
    val minFeeCoefficient: Int,
)
