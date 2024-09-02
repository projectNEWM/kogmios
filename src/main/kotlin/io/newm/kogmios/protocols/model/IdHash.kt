package io.newm.kogmios.protocols.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IdHash(
    @SerialName("id")
    val id: String,
    @SerialName("from")
    val from: String? = null,
)
