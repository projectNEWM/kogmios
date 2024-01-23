package io.newm.kogmios.protocols.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TransactionMetadata(
    @SerialName("hash")
    val hash: String,
    @SerialName("labels")
    val labels: Map<String, Label>,
)
