package io.projectnewm.kogmios.protocols.localstatequery.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DelegationsAndRewards(
    @SerialName("delegationsAndRewards")
    val delegationsAndRewards: List<String>
)
