package io.newm.kogmios.protocols.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Label(
    @SerialName("cbor")
    val cbor: String? = null,
    @SerialName("json")
    val json: MetadataValue? = null,
)
