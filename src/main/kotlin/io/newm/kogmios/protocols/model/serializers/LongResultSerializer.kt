package io.newm.kogmios.protocols.model.serializers

import io.newm.kogmios.protocols.model.result.LongResult
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.json.long

object LongResultSerializer : KSerializer<LongResult> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("LongResult")

    override fun deserialize(decoder: Decoder): LongResult {
        require(decoder is JsonDecoder)
        val element = decoder.decodeJsonElement()
        return LongResult(element.jsonPrimitive.long)
    }

    override fun serialize(
        encoder: Encoder,
        value: LongResult
    ) {
        encoder.encodeLong(value.value)
    }
}
