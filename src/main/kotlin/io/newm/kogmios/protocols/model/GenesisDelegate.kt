package io.newm.kogmios.protocols.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenesisDelegate(
    @SerialName("issuer")
    val issuer: IdHash,
    @SerialName("delegate")
    val delegate: IdHashWithVrf
)
