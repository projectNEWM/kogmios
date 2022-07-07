package io.projectnewm.kogmios.protocols.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CostModels(
    @SerialName("plutus:v1")
    val plutusV1: PlutusV1? = null,
    @SerialName("plutus:v2")
    val plutusV2: PlutusV2? = null,
)
