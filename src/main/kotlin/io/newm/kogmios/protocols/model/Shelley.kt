package io.projectnewm.kogmios.protocols.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Shelley(
    @SerialName("body")
    val body: List<TxShelley>,

    @SerialName("headerHash")
    val headerHash: String,

    @SerialName("header")
    val header: BlockHeader,
)
