package io.newm.kogmios.protocols.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RewardAccountSummary(
    @SerialName("delegate")
    val delegate: StakePool,
    @SerialName("rewards")
    val rewards: Ada,
)
