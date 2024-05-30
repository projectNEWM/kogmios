package io.newm.kogmios.protocols.model.result

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SessionDurations(
    @SerialName("max")
    val max: Double,
    @SerialName("mean")
    val mean: Double,
    @SerialName("min")
    val min: Double,
)
