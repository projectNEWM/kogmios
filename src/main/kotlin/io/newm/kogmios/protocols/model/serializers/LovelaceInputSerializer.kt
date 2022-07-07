package io.newm.kogmios.protocols.model.serializers

import io.newm.kogmios.protocols.model.LovelaceInput
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.json.long

object LovelaceInputSerializer : KSerializer<LovelaceInput> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("LovelaceInput")

    override fun deserialize(decoder: Decoder): LovelaceInput {
        require(decoder is JsonDecoder)
        val element = decoder.decodeJsonElement()
        return LovelaceInput(element.jsonPrimitive.long.toBigInteger())
    }

    override fun serialize(encoder: Encoder, value: LovelaceInput) {
        encoder.encodeLong(value.amount.longValueExact())
    }
}
