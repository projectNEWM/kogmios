package io.newm.kogmios.protocols.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MetadataResult(
    @SerialName("url")
    val url: String,
    @SerialName("hash")
    val hash: String,
)
