package io.newm.kogmios.protocols.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.apache.commons.numbers.fraction.BigFraction

@Serializable
data class ProtocolParametersUpdateThresholds(
    @SerialName("network")
    @Contextual
    val network: BigFraction,
    @SerialName("economic")
    @Contextual
    val economic: BigFraction,
    @SerialName("technical")
    @Contextual
    val technical: BigFraction,
    @SerialName("governance")
    @Contextual
    val governance: BigFraction,
)
