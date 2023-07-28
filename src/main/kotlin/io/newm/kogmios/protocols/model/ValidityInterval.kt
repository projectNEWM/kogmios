package io.newm.kogmios.protocols.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.math.BigInteger

@Serializable
data class ValidityInterval(
    @SerialName("invalidBefore")
    @Contextual
    val invalidBefore: BigInteger? = null,
    @SerialName("invalidAfter")
    @Contextual
    val invalidAfter: BigInteger? = null,
)
