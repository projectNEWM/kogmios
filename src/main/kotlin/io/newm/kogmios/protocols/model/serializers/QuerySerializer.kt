package io.newm.kogmios.protocols.model.serializers

import io.newm.kogmios.protocols.model.Query
import io.newm.kogmios.protocols.model.QueryChainTip
import io.newm.kogmios.protocols.model.QueryPoolParameters
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.json.*

object QuerySerializer : JsonContentPolymorphicSerializer<Query>(Query::class) {
    override fun selectDeserializer(element: JsonElement): DeserializationStrategy<out Query> {
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
