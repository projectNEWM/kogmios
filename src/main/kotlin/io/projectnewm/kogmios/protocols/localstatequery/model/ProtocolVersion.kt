package io.projectnewm.kogmios.protocols.localstatequery.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProtocolVersion(
    @SerialName("major")
    val major: Int,
    @SerialName("minor")
    val minor: Int
)