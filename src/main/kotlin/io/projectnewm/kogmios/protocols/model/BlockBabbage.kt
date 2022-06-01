package io.projectnewm.kogmios.protocols.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BlockBabbage(
    @SerialName("babbage")
    val babbage: Babbage,
) : Block()
