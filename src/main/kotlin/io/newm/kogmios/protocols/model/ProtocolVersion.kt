package io.newm.kogmios.protocols.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProtocolVersion(
    @SerialName("major")
    val major: Int,
    @SerialName("minor")
    val minor: Int,
    @SerialName("patch")
    val patch: Int? = null,
)
