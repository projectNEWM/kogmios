package io.newm.kogmios.protocols.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TxAllegra(
    @SerialName("id")
    val id: String,

    @SerialName("raw")
    val raw: String? = null,

    @SerialName("body")
    val body: TxBodyAllegra,

    @SerialName("witness")
    val witness: TxWitness? = null,

    @SerialName("metadata")
    val metadata: TxMetadata?,
)
