package io.projectnewm.kogmios.protocols.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TxShelley(
    @SerialName("id")
    val id: String,

    @SerialName("raw")
    val raw: String? = null,

    @SerialName("body")
    val body: TxBodyShelley,

    @SerialName("witness")
    val witness: TxWitness? = null,

    @SerialName("metadata")
    val metadata: TxMetadata?,
)
