package io.newm.kogmios.protocols.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.math.BigInteger

@Serializable
data class Seconds(
    @SerialName("seconds")
    @Contextual
    val seconds: BigInteger,
)
