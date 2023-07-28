package io.newm.kogmios.serializers

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import org.apache.commons.numbers.fraction.BigFraction
import java.math.BigDecimal

object BigFractionSerializer : KSerializer<BigFraction> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("io.newm.kogmios.serializers.BigFractionSerializer", PrimitiveKind.STRING)

    override fun serialize(
        encoder: Encoder,
        value: BigFraction
    ) {
        val rationalString = value.toString().replace(" ", "")
        encoder.encodeString(rationalString)
    }

    override fun deserialize(decoder: Decoder): BigFraction {
        val decodedString = decoder.decodeString()
        return if ('/' in decodedString) {
            // decode rational like "3/4"
            BigFraction.parse(decodedString)
        } else {
            // decode decimal like "0.75"
            val bigDecimal = BigDecimal(decodedString)
            val scale = bigDecimal.scale()
            val denominator = BigDecimal.TEN.pow(scale).toBigIntegerExact()
            val numerator = bigDecimal.movePointRight(scale).toBigIntegerExact()
            BigFraction.of(numerator, denominator)
        }
    }
}
