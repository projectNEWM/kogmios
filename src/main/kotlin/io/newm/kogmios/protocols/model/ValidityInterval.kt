package io.newm.kogmios.protocols.model

import java.math.BigInteger

data class ValidityInterval(
    val invalidBefore: BigInteger? = null,
    val invalidAfter: BigInteger? = null,
)
