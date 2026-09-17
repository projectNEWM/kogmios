package io.newm.kogmios.serializers

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import java.math.BigDecimal
import java.math.BigInteger
import org.apache.commons.numbers.fraction.BigFraction

internal object BigFractionSerializer : JsonSerializer<BigFraction>() {
    override fun serialize(
        value: BigFraction,
        generator: JsonGenerator,
        serializers: SerializerProvider
    ) {
        generator.writeString(value.toString().replace(" ", ""))
    }
}

internal object BigFractionDeserializer : StdDeserializer<BigFraction>(BigFraction::class.java) {
    override fun deserialize(
        parser: JsonParser,
        context: DeserializationContext
    ): BigFraction {
        val value = parser.valueAsString
        return try {
            if ('/' in value) {
                BigFraction.parse(value)
            } else {
                val decimal = BigDecimal(value)
                if (decimal.scale() >= 0) {
                    BigFraction.of(decimal.unscaledValue(), BigInteger.TEN.pow(decimal.scale()))
                } else {
                    BigFraction.of(decimal.unscaledValue().multiply(BigInteger.TEN.pow(-decimal.scale())), BigInteger.ONE)
                }
            }
        } catch (exception: RuntimeException) {
            throw context.weirdStringException(value, BigFraction::class.java, exception.message)
        }
    }
}
