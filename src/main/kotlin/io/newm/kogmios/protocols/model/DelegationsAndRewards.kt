package io.newm.kogmios.protocols.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DelegationsAndRewards(
    @SerialName("delegationsAndRewards")
    val delegationsAndRewards: List<String>,
)
