package io.projectnewm.kogmios.protocols.localstatequery.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CostModels(
    @SerialName("plutus:v1")
    val plutusV1: PlutusV1
)