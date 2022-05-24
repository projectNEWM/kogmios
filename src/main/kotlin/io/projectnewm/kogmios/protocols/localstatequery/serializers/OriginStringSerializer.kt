package io.projectnewm.kogmios.protocols.localstatequery.serializers

import io.projectnewm.kogmios.protocols.localstatequery.model.OriginString
import io.projectnewm.kogmios.protocols.localstatequery.model.PointDetailOrOrigin
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonDecoder

object OriginStringSerializer : KSerializer<PointDetailOrOrigin> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("Origin")

    override fun deserialize(decoder: Decoder): PointDetailOrOrigin {
        require(decoder is JsonDecoder)
        return OriginString(decoder.decodeString())
    }

    override fun serialize(encoder: Encoder, value: PointDetailOrOrigin) {
        encoder.encodeString((value as OriginString).origin)
    }
}
