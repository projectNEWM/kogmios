package io.newm.kogmios.protocols.model

import io.newm.kogmios.protocols.model.serializers.UtxoOutputValueSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = UtxoOutputValueSerializer::class)
data class UtxoOutputValue(
    @SerialName("ada")
    val ada: Ada,
    @SerialName("assets")
    val assets: List<Asset>? = null,
)
