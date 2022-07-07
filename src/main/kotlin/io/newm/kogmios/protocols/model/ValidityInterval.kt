package io.newm.kogmios.protocols.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.math.BigInteger

@Serializable
data class ValidityInterval(
    @SerialName("invalidBefore")
    @Contextual
    val invalidBefore: BigInteger?,

    @SerialName("invalidHereafter")
    @Contextual
    val invalidHereafter: BigInteger?,
)
