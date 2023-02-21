package io.newm.kogmios.protocols.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PoolParameters(
    @SerialName("poolParameters")
    val poolParameters: List<String>,
)
