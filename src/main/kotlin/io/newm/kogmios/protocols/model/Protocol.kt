package io.newm.kogmios.protocols.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Protocol(
    @SerialName("version")
    val version: Version,
    @SerialName("software")
    val software: Software,
    @SerialName("update")
    val update: Update,
)
