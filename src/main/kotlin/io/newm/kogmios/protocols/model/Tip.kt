package io.projectnewm.kogmios.protocols.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Tip(
    @SerialName("slot")
    val slot: Long,
    @SerialName("hash")
    val hash: String,
    @SerialName("blockNo")
    val blockNo: Long,
)
