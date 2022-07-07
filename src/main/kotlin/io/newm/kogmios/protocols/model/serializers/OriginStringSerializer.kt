package io.newm.kogmios.protocols.model.serializers

import io.newm.kogmios.protocols.model.OriginString
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonDecoder

object OriginStringSerializer : KSerializer<OriginString> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("Origin")

    override fun deserialize(decoder: Decoder): OriginString {
        require(decoder is JsonDecoder)
        return OriginString(decoder.decodeString())
    }

    override fun serialize(encoder: Encoder, value: OriginString) {
        encoder.encodeString(value.origin)
    }
}
