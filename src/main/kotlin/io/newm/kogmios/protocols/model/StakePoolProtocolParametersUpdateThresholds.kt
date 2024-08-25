package io.newm.kogmios.protocols.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.apache.commons.numbers.fraction.BigFraction

@Serializable
data class StakePoolProtocolParametersUpdateThresholds(
    @SerialName("security")
    @Contextual
    val security: BigFraction,
)
