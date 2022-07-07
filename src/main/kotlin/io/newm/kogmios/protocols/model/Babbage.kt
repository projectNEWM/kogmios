package io.projectnewm.kogmios.protocols.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Babbage(
    @SerialName("body")
    val body: List<TxBabbage>,

    @SerialName("headerHash")
    val headerHash: String,

    @SerialName("header")
    val header: BlockHeader,
)
