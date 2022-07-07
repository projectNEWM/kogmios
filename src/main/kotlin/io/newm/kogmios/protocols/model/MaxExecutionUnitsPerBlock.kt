package io.projectnewm.kogmios.protocols.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MaxExecutionUnitsPerBlock(
    @SerialName("memory")
    val memory: Long,
    @SerialName("steps")
    val steps: Long
)
