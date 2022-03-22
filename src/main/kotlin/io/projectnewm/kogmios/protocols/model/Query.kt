package io.projectnewm.kogmios.protocols.model

import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerialName
import kotlinx.serialization.json.*

@kotlinx.serialization.Serializable(with = QuerySerializer::class)
abstract class Query

@kotlinx.serialization.Serializable
data class QueryChainTip(
    @SerialName("query")
    val query: String = "chainTip"
) : Query()

@kotlinx.serialization.Serializable
data class QueryPoolParameters(
    @SerialName("query")
    val query: PoolParameters
) : Query()

@kotlinx.serialization.Serializable
data class PoolParameters(
    @SerialName("poolParameters")
    val poolParameters: List<String>
)

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