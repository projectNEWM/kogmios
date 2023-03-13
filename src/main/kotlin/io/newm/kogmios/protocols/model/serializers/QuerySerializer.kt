package io.newm.kogmios.protocols.model.serializers

import io.newm.kogmios.protocols.model.Query
import io.newm.kogmios.protocols.model.QueryChainTip
import io.newm.kogmios.protocols.model.QueryPoolParameters
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.jsonObject

object QuerySerializer : JsonContentPolymorphicSerializer<Query>(Query::class) {
    override fun selectDeserializer(element: JsonElement): DeserializationStrategy<Query> {
        return when ((element.jsonObject["query"] as? JsonPrimitive)?.contentOrNull) {
            "chainTip" -> QueryChainTip.serializer()
            else -> {
                if (element.jsonObject["query"]?.jsonObject?.let { "poolParameters" in it } == true) {
                    QueryPoolParameters.serializer()
                } else {
                    Query.serializer()
                }
            }
        }
    }
}
