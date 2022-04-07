package io.projectnewm.kogmios.protocols.localstatequery.serializers

import io.projectnewm.kogmios.protocols.localstatequery.model.LongQueryResult
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.json.long

object LongQueryResultSerializer : KSerializer<LongQueryResult> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("LongQueryResult")

    override fun deserialize(decoder: Decoder): LongQueryResult {
        require(decoder is JsonDecoder)
        val element = decoder.decodeJsonElement()
        return LongQueryResult(element.jsonPrimitive.long)
    }

    override fun serialize(encoder: Encoder, value: LongQueryResult) {
        encoder.encodeLong(value.value)
    }
}
