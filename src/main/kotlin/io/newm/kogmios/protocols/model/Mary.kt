package io.newm.kogmios.protocols.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Mary(
    @SerialName("body")
    val body: List<TxMary>,

    @SerialName("headerHash")
    val headerHash: String,

    @SerialName("header")
    val header: BlockHeader,
)
