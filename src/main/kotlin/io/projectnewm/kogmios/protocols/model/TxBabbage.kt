package io.projectnewm.kogmios.protocols.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TxBabbage(
    @SerialName("id")
    val id: String,

    @SerialName("raw")
    val raw: String? = null,

    @SerialName("inputSource")
    val inputSource: String, // "inputs" or "collaterals"

    @SerialName("body")
    val body: TxBodyBabbage,

    @SerialName("witness")
    val witness: TxWitness? = null,

    @SerialName("metadata")
    val metadata: TxMetadata?,
)
