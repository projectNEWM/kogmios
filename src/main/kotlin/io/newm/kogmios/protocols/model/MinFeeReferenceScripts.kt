package io.newm.kogmios.protocols.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MinFeeReferenceScripts(
    @SerialName("range")
    val range: Int,
    @SerialName("base")
    val base: Double,
    @SerialName("multiplier")
    val multiplier: Double,
)
