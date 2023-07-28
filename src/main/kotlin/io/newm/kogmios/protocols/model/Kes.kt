package io.newm.kogmios.protocols.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Kes(
    @SerialName("period")
    val period: Long,
    @SerialName("verificationKey")
    val verificationKey: String,
)
