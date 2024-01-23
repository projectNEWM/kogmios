package io.newm.kogmios.protocols.model.serializers

import io.newm.kogmios.protocols.model.result.BooleanResult
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.boolean
import kotlinx.serialization.json.jsonPrimitive

object BooleanResultSerializer : KSerializer<BooleanResult> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("BooleanResult")

    override fun deserialize(decoder: Decoder): BooleanResult {
        require(decoder is JsonDecoder)
        val element = decoder.decodeJsonElement()
        return BooleanResult(element.jsonPrimitive.boolean)
    }

    override fun serialize(
        encoder: Encoder,
        value: BooleanResult
    ) {
        encoder.encodeBoolean(value.value)
    }
}
