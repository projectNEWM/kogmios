package io.newm.kogmios.protocols.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TxRedeemer(
    @SerialName("executionUnits")
    val executionUnits: ExecutionUnits,

    @SerialName("redeemer")
    val redeemer: String,
)
