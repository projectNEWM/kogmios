package io.newm.kogmios.protocols.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed class ParamsUtxo : Params()

@Serializable
data class ParamsUtxoByOutputReferences(
    @SerialName("outputReferences")
    val outputReferences: List<UtxoOutputReference>,
) : ParamsUtxo()

@Serializable
data class ParamsUtxoByAddresses(
    @SerialName("addresses")
    val addresses: List<String>,
) : ParamsUtxo()
