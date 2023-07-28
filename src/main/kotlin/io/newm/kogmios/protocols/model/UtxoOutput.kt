package io.newm.kogmios.protocols.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UtxoOutput(
    @SerialName("address")
    val address: String,
    @SerialName("value")
    val value: UtxoOutputValue,
    @SerialName("datumHash")
    val datumHash: String? = null,
    @SerialName("datum")
    val datum: String? = null,
    @SerialName("script")
    val script: Script? = null,
)
