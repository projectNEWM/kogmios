package io.projectnewm.kogmios.protocols.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BlockAllegra(
    @SerialName("allegra")
    val allegra: Allegra,
) : Block()
