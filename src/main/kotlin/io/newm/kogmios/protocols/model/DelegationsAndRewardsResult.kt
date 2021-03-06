package io.newm.kogmios.protocols.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DelegationsAndRewardsResult(
    @SerialName("delegate")
    val delegate: String,
    @SerialName("rewards")
    val rewards: Long,
)
