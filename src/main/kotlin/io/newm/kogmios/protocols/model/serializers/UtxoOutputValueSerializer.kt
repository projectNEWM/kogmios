package io.newm.kogmios.protocols.model.serializers

import io.newm.kogmios.protocols.model.Ada
import io.newm.kogmios.protocols.model.Asset
import io.newm.kogmios.protocols.model.Lovelace
import io.newm.kogmios.protocols.model.UtxoOutputValue
import io.newm.kogmios.serializers.BigIntegerSerializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonDecoder

object UtxoOutputValueSerializer : KSerializer<UtxoOutputValue> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("UtxoOutputValue")

    private val delegateMapSerializer =
        MapSerializer(String.serializer(), MapSerializer(String.serializer(), BigIntegerSerializer))

    override fun deserialize(decoder: Decoder): UtxoOutputValue {
        require(decoder is JsonDecoder)
        val elements = delegateMapSerializer.deserialize(decoder)
        val adaMap = elements["ada"] ?: throw IllegalStateException("ada value not found in UtxoOutputValue")
        val lovelace = adaMap["lovelace"] ?: throw IllegalStateException("lovelace value not found in UtxoOutputValue")
        val assets =
            elements.entries.filterNot { it.key == "ada" }.map {
                Asset(
                    policyId = it.key,
                    name = it.value.keys.first(),
                    quantity = it.value.values.first()
                )
            }
        return UtxoOutputValue(
            ada = Ada(Lovelace(lovelace)),
            assets = assets
        )
    }

    override fun serialize(
        encoder: Encoder,
        value: UtxoOutputValue
    ) {
        // not implemented
    }
}
