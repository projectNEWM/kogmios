package io.newm.kogmios.protocols.model.serializers

import io.newm.kogmios.protocols.model.fault.StringFaultData
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonDecoder

object StringFaultDataSerializer : KSerializer<StringFaultData> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("StringFaultData")

    override fun deserialize(decoder: Decoder): StringFaultData {
        require(decoder is JsonDecoder)
        return StringFaultData(decoder.decodeString())
    }

    override fun serialize(
        encoder: Encoder,
        value: StringFaultData
    ) {
        encoder.encodeString(value.value)
    }
}
