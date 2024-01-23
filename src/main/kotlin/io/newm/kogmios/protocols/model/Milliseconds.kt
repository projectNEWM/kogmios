package io.newm.kogmios.protocols.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.math.BigInteger

@Serializable
data class Milliseconds(
    @SerialName("milliseconds")
    @Contextual
    val milliseconds: BigInteger,
)
