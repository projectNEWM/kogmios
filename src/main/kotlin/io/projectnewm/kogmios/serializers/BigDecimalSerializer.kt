package io.projectnewm.kogmios.serializers

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.math.BigDecimal

@Serializer(forClass = BigDecimal::class)
object BigDecimalSerializer : KSerializer<BigDecimal> {

    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("BigDecimal", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: BigDecimal) {
        encoder.encodeString(value.toString())
    }

    override fun deserialize(decoder: Decoder): BigDecimal {
        val decodedString = decoder.decodeString()
        return if (decodedString.contains('/')) {
            // decode rational like "3/4"
            decodedString.split('/', ignoreCase = true, limit = 2).let {
                BigDecimal(it[0]).divide(BigDecimal(it[1]))
            }
        } else {
            // decode decimal like "0.75"
            BigDecimal(decodedString)
        }
    }
}
