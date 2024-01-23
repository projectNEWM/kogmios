package io.newm.kogmios.protocols.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ConwayGenesisProtocolParameters(
    @SerialName("stakePoolVotingThresholds")
    val stakePoolVotingThresholds: StakePoolVotingThresholds,
    @SerialName("constitutionalCommitteeMinSize")
    val constitutionalCommitteeMinSize: Long,
    @SerialName("constitutionalCommitteeMaxTermLength")
    val constitutionalCommitteeMaxTermLength: Long,
    @SerialName("governanceActionLifetime")
    val governanceActionLifetime: Long,
    @SerialName("governanceActionDeposit")
    val governanceActionDeposit: Ada,
    @SerialName("delegateRepresentativeVotingThresholds")
    val delegateRepresentativeVotingThresholds: DelegateRepresentativeVotingThresholds,
    @SerialName("delegateRepresentativeDeposit")
    val delegateRepresentativeDeposit: Ada,
    @SerialName("delegateRepresentativeMaxIdleTime")
    val delegateRepresentativeMaxIdleTime: Long,
)
