package io.projectnewm.kogmios.protocols.localstatequery.model

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class PointDetail(
    @SerialName("slot")
    val slot: Long,
    @SerialName("hash")
    val hash: String,
)
