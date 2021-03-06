package io.newm.kogmios.protocols.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TxWitness(
    @SerialName("signatures")
    val signatures: Map<String, String>,

    @SerialName("scripts")
    val scripts: Map<String, Script>,

    @SerialName("bootstrap")
    val bootstrap: List<TxShelleyBootstrap>
)
