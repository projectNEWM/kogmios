package io.newm.kogmios.protocols.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BlockAlonzo(
    @SerialName("alonzo")
    val alonzo: Alonzo,
) : Block()
