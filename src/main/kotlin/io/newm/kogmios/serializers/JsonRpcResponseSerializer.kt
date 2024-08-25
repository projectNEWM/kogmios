package io.newm.kogmios.serializers

import io.newm.kogmios.protocols.messages.JsonRpcErrorResponse
import io.newm.kogmios.protocols.messages.JsonRpcResponse
import io.newm.kogmios.protocols.messages.JsonRpcSuccessResponse
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonObject

object JsonRpcResponseSerializer : JsonContentPolymorphicSerializer<JsonRpcResponse>(JsonRpcResponse::class) {
    override fun selectDeserializer(element: JsonElement): DeserializationStrategy<JsonRpcResponse> =
        if ("error" in element.jsonObject) {
            JsonRpcErrorResponse.serializer()
        } else {
            JsonRpcSuccessResponse.serializer()
        }
}
