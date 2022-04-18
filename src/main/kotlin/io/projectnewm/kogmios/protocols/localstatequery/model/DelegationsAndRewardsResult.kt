package io.projectnewm.kogmios.protocols.localstatequery.model

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class DelegationsAndRewardsResult(
    @SerialName("delegate")
    val delegate: String,
    @SerialName("rewards")
    val rewards: Long,
)
