package io.newm.kogmios.serializers

import com.fasterxml.jackson.core.JsonFactory
import com.fasterxml.jackson.core.StreamReadConstraints
import com.fasterxml.jackson.core.StreamWriteConstraints
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.fasterxml.jackson.module.kotlin.KotlinFeature
import com.fasterxml.jackson.module.kotlin.KotlinModule
import io.newm.kogmios.protocols.messages.JsonRpcResponse
import io.newm.kogmios.protocols.model.MetadataList
import io.newm.kogmios.protocols.model.MetadataMap
import io.newm.kogmios.protocols.model.MetadataValue
import io.newm.kogmios.protocols.model.PointDetailOrOrigin
import io.newm.kogmios.protocols.model.PointOrOrigin
import io.newm.kogmios.protocols.model.UtxoOutputValue
import io.newm.kogmios.protocols.model.serializers.MetadataListDeserializer
import io.newm.kogmios.protocols.model.serializers.MetadataListSerializer
import io.newm.kogmios.protocols.model.serializers.MetadataMapDeserializer
import io.newm.kogmios.protocols.model.serializers.MetadataMapSerializer
import io.newm.kogmios.protocols.model.serializers.MetadataValueDeserializer
import io.newm.kogmios.protocols.model.serializers.PointDetailOrOriginDeserializer
import io.newm.kogmios.protocols.model.serializers.PointOrOriginDeserializer
import io.newm.kogmios.protocols.model.serializers.UtxoOutputValueDeserializer
import io.newm.kogmios.protocols.model.serializers.UtxoOutputValueSerializer
import io.newm.kogmios.protocols.model.result.LongResult
import kotlin.time.Instant
import org.apache.commons.numbers.fraction.BigFraction

internal object OgmiosJson {
    private val module =
        SimpleModule().apply {
            addSerializer(BigFraction::class.java, BigFractionSerializer)
            addDeserializer(BigFraction::class.java, BigFractionDeserializer)
            addSerializer(Instant::class.java, InstantSerializer)
            addDeserializer(Instant::class.java, InstantDeserializer)
            addDeserializer(LongResult::class.java, LongResultDeserializer)
            addDeserializer(JsonRpcResponse::class.java, JsonRpcResponseDeserializer())
            addDeserializer(PointDetailOrOrigin::class.java, PointDetailOrOriginDeserializer())
            addDeserializer(PointOrOrigin::class.java, PointOrOriginDeserializer())
            addSerializer(UtxoOutputValue::class.java, UtxoOutputValueSerializer())
            addDeserializer(UtxoOutputValue::class.java, UtxoOutputValueDeserializer())
            addDeserializer(MetadataValue::class.java, MetadataValueDeserializer())
            addSerializer(MetadataMap::class.java, MetadataMapSerializer())
            addDeserializer(MetadataMap::class.java, MetadataMapDeserializer())
            addSerializer(MetadataList::class.java, MetadataListSerializer())
            addDeserializer(MetadataList::class.java, MetadataListDeserializer())
        }

    val mapper =
        JsonMapper
            .builder(
                JsonFactory
                    .builder()
                    .streamReadConstraints(StreamReadConstraints.builder().maxNestingDepth(16_384).build())
                    .streamWriteConstraints(StreamWriteConstraints.builder().maxNestingDepth(16_384).build())
                    .build(),
            ).addModule(module)
            .addModule(
                KotlinModule
                    .Builder()
                    .enable(KotlinFeature.StrictNullChecks)
                    .enable(KotlinFeature.SingletonSupport)
                    .enable(KotlinFeature.KotlinPropertyNameAsImplicitName)
                    .build(),
            ).disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            .enable(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES)
            .enable(DeserializationFeature.FAIL_ON_TRAILING_TOKENS)
            .disable(DeserializationFeature.ACCEPT_FLOAT_AS_INT)
            .enable(DeserializationFeature.USE_BIG_INTEGER_FOR_INTS)
            .enable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)
            .build()
}

private object InstantSerializer : JsonSerializer<Instant>() {
    override fun serialize(
        value: Instant,
        generator: JsonGenerator,
        serializers: SerializerProvider
    ) {
        generator.writeString(value.toString())
    }
}

private object InstantDeserializer : StdDeserializer<Instant>(Instant::class.java) {
    override fun deserialize(
        parser: JsonParser,
        context: DeserializationContext
    ): Instant =
        try {
            Instant.parse(parser.valueAsString)
        } catch (exception: IllegalArgumentException) {
            throw context.weirdStringException(parser.valueAsString, Instant::class.java, exception.message)
        }
}

private object LongResultDeserializer : StdDeserializer<LongResult>(LongResult::class.java) {
    override fun deserialize(
        parser: JsonParser,
        context: DeserializationContext
    ): LongResult = LongResult(parser.longValue)
}
