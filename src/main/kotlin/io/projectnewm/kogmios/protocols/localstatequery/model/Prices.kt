package io.projectnewm.kogmios.protocols.localstatequery.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.math.BigDecimal

@Serializable
data class Prices(
    @SerialName("memory")
    @Contextual
    val memory: BigDecimal,
    @SerialName("steps")
    @Contextual
    val steps: BigDecimal
)
