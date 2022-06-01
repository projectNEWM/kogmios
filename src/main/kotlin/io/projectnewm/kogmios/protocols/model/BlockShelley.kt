package io.projectnewm.kogmios.protocols.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BlockShelley(
    @SerialName("shelley")
    val shelley: Shelley,
) : Block()
