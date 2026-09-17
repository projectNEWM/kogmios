package io.newm.kogmios.serializers

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider

internal class DeepJsonNodeSerializer : JsonSerializer<JsonNode>() {
    override fun serialize(
        value: JsonNode,
        generator: JsonGenerator,
        serializers: SerializerProvider
    ) {
        value.traverse(generator.codec).use { parser ->
            parser.nextToken()
            generator.copyCurrentStructure(parser)
        }
    }
}
