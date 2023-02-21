package io.newm.kogmios.protocols.model.serializers

import io.newm.kogmios.ClientImpl.Companion.json
import io.newm.kogmios.protocols.model.EraSummariesQueryResult
import io.newm.kogmios.protocols.model.EraSummary
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.encoding.encodeCollection
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.jsonArray

object EraSummariesQueryResultSerializer : KSerializer<EraSummariesQueryResult> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("EraSummariesQueryResult")

    override fun deserialize(decoder: Decoder): EraSummariesQueryResult {
        require(decoder is JsonDecoder)

        return EraSummariesQueryResult(
            decoder.decodeJsonElement().jsonArray.map {
                json.decodeFromJsonElement(EraSummary.serializer(), it)
            },
        )
    }

    override fun serialize(encoder: Encoder, value: EraSummariesQueryResult) {
        encoder.encodeCollection(descriptor, value.value) { _, eraSummary ->
            encoder.encodeSerializableValue(EraSummary.serializer(), eraSummary)
        }
    }
}
