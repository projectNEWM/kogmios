package io.projectnewm.kogmios.protocols.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Alonzo(
    @SerialName("body")
    val body: List<TxAlonzo>,

    @SerialName("headerHash")
    val headerHash: String,

    @SerialName("header")
    val header: BlockHeader,
)
