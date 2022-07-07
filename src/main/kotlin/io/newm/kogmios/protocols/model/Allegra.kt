package io.newm.kogmios.protocols.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Allegra(
    @SerialName("body")
    val body: List<TxAllegra>,

    @SerialName("headerHash")
    val headerHash: String,

    @SerialName("header")
    val header: BlockHeader,
)
