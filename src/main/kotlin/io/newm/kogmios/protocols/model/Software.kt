package io.newm.kogmios.protocols.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Software(
    @SerialName("appName")
    val appName: String,
    @SerialName("number")
    val number: Long,
)
