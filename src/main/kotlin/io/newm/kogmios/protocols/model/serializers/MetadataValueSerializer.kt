package io.newm.kogmios.protocols.model.serializers

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import io.newm.kogmios.protocols.model.MetadataBytes
import io.newm.kogmios.protocols.model.MetadataInteger
import io.newm.kogmios.protocols.model.MetadataList
import io.newm.kogmios.protocols.model.MetadataMap
import io.newm.kogmios.protocols.model.MetadataString
import io.newm.kogmios.protocols.model.MetadataValue

internal class MetadataValueDeserializer : StdDeserializer<MetadataValue>(MetadataValue::class.java) {
    override fun deserialize(
        parser: JsonParser,
        context: DeserializationContext
    ): MetadataValue {
        val node = context.readTree(parser)
        val target =
            when {
                node.has("map") -> MetadataMap::class.java
                node.has("list") -> MetadataList::class.java
                node.has("string") -> MetadataString::class.java
                node.has("int") -> MetadataInteger::class.java
                node.has("bytes") -> MetadataBytes::class.java
                else -> return context.reportInputMismatch(MetadataValue::class.java, "unknown metadata value")
            }
        return context.readTreeAsValue(node, target)
    }
}

internal class MetadataMapDeserializer : StdDeserializer<MetadataMap>(MetadataMap::class.java) {
    override fun deserialize(
        parser: JsonParser,
        context: DeserializationContext
    ): MetadataMap {
        val node = context.readTree(parser)
        val result = MetadataMap()
        node.path("map").forEach { entry ->
            val key = context.readTreeAsValue(entry.path("k"), MetadataValue::class.java)
            val value = context.readTreeAsValue(entry.path("v"), MetadataValue::class.java)
            result[key] = value
        }
        return result
    }
}

internal class MetadataListDeserializer : StdDeserializer<MetadataList>(MetadataList::class.java) {
    override fun deserialize(
        parser: JsonParser,
        context: DeserializationContext
    ): MetadataList {
        val node = context.readTree(parser)
        return MetadataList().apply {
            node.path("list").forEach { add(context.readTreeAsValue(it, MetadataValue::class.java)) }
        }
    }
}

internal class MetadataMapSerializer : JsonSerializer<MetadataMap>() {
    override fun serialize(
        value: MetadataMap,
        generator: JsonGenerator,
        serializers: SerializerProvider
    ) {
        generator.writeStartObject()
        generator.writeArrayFieldStart("map")
        value.forEach { (key, item) ->
            generator.writeStartObject()
            generator.writeObjectField("k", key)
            generator.writeObjectField("v", item)
            generator.writeEndObject()
        }
        generator.writeEndArray()
        generator.writeEndObject()
    }
}

internal class MetadataListSerializer : JsonSerializer<MetadataList>() {
    override fun serialize(
        value: MetadataList,
        generator: JsonGenerator,
        serializers: SerializerProvider
    ) {
        generator.writeStartObject()
        generator.writeArrayFieldStart("list")
        value.forEach(generator::writeObject)
        generator.writeEndArray()
        generator.writeEndObject()
    }
}
