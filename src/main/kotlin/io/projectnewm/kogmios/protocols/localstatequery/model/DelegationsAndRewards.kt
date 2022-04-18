package io.projectnewm.kogmios.protocols.localstatequery.model

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class DelegationsAndRewards(
    @SerialName("delegationsAndRewards")
    val delegationsAndRewards: List<String>
)
