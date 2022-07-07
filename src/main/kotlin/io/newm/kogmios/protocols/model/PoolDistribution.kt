package io.newm.kogmios.protocols.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.math.BigDecimal

@Serializable
data class PoolDistribution(
    @SerialName("stake")
    @Contextual
    val stake: BigDecimal,
    @SerialName("vrf")
    val vrf: String,
)
