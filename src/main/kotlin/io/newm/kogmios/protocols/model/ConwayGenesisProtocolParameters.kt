package io.newm.kogmios.protocols.model

import com.fasterxml.jackson.annotation.JsonProperty

data class ConwayGenesisProtocolParameters(
    @param:JsonProperty(value = "stakePoolVotingThresholds", required = true)
    @get:JsonProperty("stakePoolVotingThresholds")
    val stakePoolVotingThresholds: StakePoolVotingThresholds,
    @param:JsonProperty(value = "constitutionalCommitteeMinSize", required = true)
    @get:JsonProperty("constitutionalCommitteeMinSize")
    val constitutionalCommitteeMinSize: Long,
    @param:JsonProperty(value = "constitutionalCommitteeMaxTermLength", required = true)
    @get:JsonProperty("constitutionalCommitteeMaxTermLength")
    val constitutionalCommitteeMaxTermLength: Long,
    @param:JsonProperty(value = "governanceActionLifetime", required = true)
    @get:JsonProperty("governanceActionLifetime")
    val governanceActionLifetime: Long,
    @param:JsonProperty(value = "governanceActionDeposit", required = true)
    @get:JsonProperty("governanceActionDeposit")
    val governanceActionDeposit: Ada,
    @param:JsonProperty(value = "delegateRepresentativeVotingThresholds", required = true)
    @get:JsonProperty("delegateRepresentativeVotingThresholds")
    val delegateRepresentativeVotingThresholds: DelegateRepresentativeVotingThresholds,
    @param:JsonProperty(value = "delegateRepresentativeDeposit", required = true)
    @get:JsonProperty("delegateRepresentativeDeposit")
    val delegateRepresentativeDeposit: Ada,
    @param:JsonProperty(value = "delegateRepresentativeMaxIdleTime", required = true)
    @get:JsonProperty("delegateRepresentativeMaxIdleTime")
    val delegateRepresentativeMaxIdleTime: Long,
)
