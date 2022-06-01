package io.projectnewm.kogmios.protocols.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.json.long
import java.math.BigInteger

@Serializable
sealed class NonMyopicMemberRewardsInput

@Serializable(with = LovelaceInputSerializer::class)
class LovelaceInput(
    @Contextual
    val amount: BigInteger
) : NonMyopicMemberRewardsInput()

object LovelaceInputSerializer : KSerializer<LovelaceInput> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("LovelaceInput")

    override fun deserialize(decoder: Decoder): LovelaceInput {
        require(decoder is JsonDecoder)
        val element = decoder.decodeJsonElement()
        return LovelaceInput(element.jsonPrimitive.long.toBigInteger())
    }

    override fun serialize(encoder: Encoder, value: LovelaceInput) {
        encoder.encodeLong(value.amount.longValueExact())
    }
}

@Serializable(with = Blake2bDigestCredentialSerializer::class)
class Blake2bDigestCredential(
    val digest: String
) : NonMyopicMemberRewardsInput()

object Blake2bDigestCredentialSerializer : KSerializer<Blake2bDigestCredential> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("Blake2bDigestCredential")

    override fun deserialize(decoder: Decoder): Blake2bDigestCredential {
        require(decoder is JsonDecoder)
        val element = decoder.decodeJsonElement()
        return Blake2bDigestCredential(element.jsonPrimitive.content)
    }

    override fun serialize(encoder: Encoder, value: Blake2bDigestCredential) {
        encoder.encodeString(value.digest)
    }
}
