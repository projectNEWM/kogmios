package io.projectnewm.kogmios.protocols.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BlockMary(
    @SerialName("mary")
    val mary: Mary,
) : Block()
