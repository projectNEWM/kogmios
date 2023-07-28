package io.newm.kogmios.protocols.model.serializers

import io.newm.kogmios.protocols.model.Blake2bDigestCredential
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.jsonPrimitive

object Blake2bDigestCredentialSerializer : KSerializer<Blake2bDigestCredential> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("Blake2bDigestCredential")

    override fun deserialize(decoder: Decoder): Blake2bDigestCredential {
        require(decoder is JsonDecoder)
        val element = decoder.decodeJsonElement()
        return Blake2bDigestCredential(element.jsonPrimitive.content)
    }

    override fun serialize(
        encoder: Encoder,
        value: Blake2bDigestCredential
    ) {
        encoder.encodeString(value.digest)
    }
}
