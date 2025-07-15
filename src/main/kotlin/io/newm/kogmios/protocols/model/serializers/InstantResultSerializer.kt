package io.newm.kogmios.protocols.model.serializers

import io.newm.kogmios.protocols.model.result.InstantResult
import kotlin.time.Instant
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonDecoder

object InstantResultSerializer : KSerializer<InstantResult> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("InstantResult")

    override fun deserialize(decoder: Decoder): InstantResult {
        require(decoder is JsonDecoder)
        return InstantResult(Instant.parse(decoder.decodeString()))
    }

    override fun serialize(
        encoder: Encoder,
        value: InstantResult
    ) {
        encoder.encodeString(value.value.toString())
    }
}
