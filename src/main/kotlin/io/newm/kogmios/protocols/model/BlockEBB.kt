package io.newm.kogmios.protocols.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("ebb")
data class BlockEBB(
    @SerialName("era")
    override val era: String,
    @SerialName("id")
    override val id: String,
    @SerialName("ancestor")
    override val ancestor: String,
    @SerialName("height")
    override val height: Long,
) : Block
