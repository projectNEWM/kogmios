package io.projectnewm.kogmios.protocols.localstatequery.serializers

import io.projectnewm.kogmios.protocols.localstatequery.model.InstantQueryResult
import kotlinx.datetime.Instant
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.jsonPrimitive

object InstantQueryResultSerializer : KSerializer<InstantQueryResult> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("InstantQueryResult")

    override fun deserialize(decoder: Decoder): InstantQueryResult {
        require(decoder is JsonDecoder)
        val element = decoder.decodeJsonElement()
        return InstantQueryResult(Instant.parse(element.jsonPrimitive.content))
    }

    override fun serialize(encoder: Encoder, value: InstantQueryResult) {
        encoder.encodeSerializableValue(Instant.serializer(), value.value)
    }
}
