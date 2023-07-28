package io.newm.kogmios.protocols.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.apache.commons.numbers.fraction.BigFraction

@Serializable
data class PoolDistribution(
    @SerialName("stake")
    @Contextual
    val stake: BigFraction,
    @SerialName("vrf")
    val vrf: String,
)
