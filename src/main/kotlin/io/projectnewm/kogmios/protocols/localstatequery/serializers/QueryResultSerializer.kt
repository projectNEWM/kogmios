package io.projectnewm.kogmios.protocols.localstatequery.serializers

import io.projectnewm.kogmios.protocols.localstatequery.model.*
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive

object QueryResultSerializer : JsonContentPolymorphicSerializer<QueryResult>(QueryResult::class) {
    override fun selectDeserializer(element: JsonElement): DeserializationStrategy<out QueryResult> {
        return when (element) {
            is JsonObject -> {
                if ("slot" in element) {
                    QueryPointResult.serializer()
                } else if ("minFeeCoefficient" in element) {
                    QueryCurrentProtocolParametersResult.serializer()
                } else if (element.keys.firstOrNull()?.startsWith("pool1") == true) {
                    QueryPoolParametersResult.serializer()
                } else if (element.keys.isEmpty()) {
                    EmptyQueryResult.serializer()
                } else {
                    throw IllegalStateException("No Serializer found to decode: $element")
                }
            }
            is JsonPrimitive -> {
                LongQueryResult.serializer()
            }
            else -> throw IllegalStateException("No Serializer found to decode: $element")
        }
    }
}
