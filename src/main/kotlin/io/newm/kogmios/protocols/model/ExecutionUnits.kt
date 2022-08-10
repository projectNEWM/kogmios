package io.newm.kogmios.protocols.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.math.BigDecimal

@Serializable
data class ExecutionUnits(
    @SerialName("memory")
    @Contextual
    val memory: BigDecimal,
    @SerialName("steps")
    @Contextual
    val steps: BigDecimal
)
