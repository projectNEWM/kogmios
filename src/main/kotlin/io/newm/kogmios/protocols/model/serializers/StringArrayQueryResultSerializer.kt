package io.newm.kogmios.protocols.model.serializers

import io.newm.kogmios.protocols.model.StringArrayQueryResult
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.encoding.encodeCollection
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonPrimitive

object StringArrayQueryResultSerializer : KSerializer<StringArrayQueryResult> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("StringArrayQueryResult")

    override fun deserialize(decoder: Decoder): StringArrayQueryResult {
        require(decoder is JsonDecoder)
        val element = decoder.decodeJsonElement()
        return StringArrayQueryResult(element.jsonArray.map { it.jsonPrimitive.content })
    }

    override fun serialize(encoder: Encoder, value: StringArrayQueryResult) {
        encoder.encodeCollection(descriptor, value.value) { _, s ->
            encoder.encodeString(s)
        }
    }
}
