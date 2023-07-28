package io.newm.kogmios.protocols.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IdHashWithVrf(
    @SerialName("id")
    val id: String,
    @SerialName("vrfVerificationKeyHash")
    val vrfVerificationKeyHash: String,
)
