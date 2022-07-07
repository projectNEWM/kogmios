package io.newm.kogmios.protocols.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.math.BigInteger

@Serializable
data class UtxoOutputValue(
    @SerialName("coins")
    @Contextual
    val coins: BigInteger,

    @SerialName("assets")
    val assets: Map<String, @Contextual BigInteger>? = null,
)
