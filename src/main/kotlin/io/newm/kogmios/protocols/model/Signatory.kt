package io.newm.kogmios.protocols.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Signatory(
    @SerialName("key")
    val key: String,
    @SerialName("signature")
    val signature: String,
    @SerialName("chainCode")
    val chainCode: String? = null,
    @SerialName("addressAttributes")
    val addressAttributes: String? = null,
)
