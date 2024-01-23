package io.newm.kogmios.protocols.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TxShelleyBootstrap(
    @SerialName("signature")
    val signature: String,
    @SerialName("key")
    val key: String,
    @SerialName("chainCode")
    val chainCode: String?,
    @SerialName("addressAttributes")
    val addressAttributes: String?,
)
