package io.newm.kogmios.serializers

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import io.newm.kogmios.protocols.messages.JsonRpcErrorResponse
import io.newm.kogmios.protocols.messages.JsonRpcResponse
import io.newm.kogmios.protocols.messages.JsonRpcSuccessResponse

internal class JsonRpcResponseDeserializer : StdDeserializer<JsonRpcResponse>(JsonRpcResponse::class.java) {
    override fun deserialize(
        parser: JsonParser,
        context: DeserializationContext
    ): JsonRpcResponse {
        val node = context.readTree(parser)
        return if (node.has("error")) {
            context.readTreeAsValue(node, JsonRpcErrorResponse::class.java)
        } else {
            context.readTreeAsValue(node, JsonRpcSuccessResponse::class.java)
        }
    }
}
