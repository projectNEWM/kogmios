package io.newm.kogmios.protocols.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MaxExecutionUnitsPerTransaction(
    @SerialName("memory")
    val memory: Long,
    @SerialName("steps")
    val steps: Long
)
