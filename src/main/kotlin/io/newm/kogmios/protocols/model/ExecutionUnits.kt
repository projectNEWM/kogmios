package io.newm.kogmios.protocols.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.math.BigInteger

@Serializable
data class ExecutionUnits(
    @Contextual
    @SerialName("memory")
    val memory: BigInteger,
    @Contextual
    @SerialName("cpu")
    val cpu: BigInteger,
)
