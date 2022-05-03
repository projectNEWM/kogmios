package io.projectnewm.kogmios.protocols.localstatequery.serializers

import io.projectnewm.kogmios.protocols.localstatequery.model.*
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.json.*

object QueryResultSerializer : JsonContentPolymorphicSerializer<QueryResult>(QueryResult::class) {
    override fun selectDeserializer(element: JsonElement): DeserializationStrategy<out QueryResult> {
        return when (element) {
            is JsonObject -> {
                if (("hash" in element) and ("slot" in element)) {
                    QueryPointResult.serializer()
                } else if ("minFeeCoefficient" in element) {
                    QueryCurrentProtocolParametersResult.serializer()
                } else if (("time" in element) and ("epoch" in element) and ("slot" in element)) {
                    Bound.serializer()
                } else if (element.keys.firstOrNull()?.startsWith("pool1") == true) {
                    QueryPoolParametersResult.serializer()
                } else if (element.keys.firstOrNull()?.matches(Regex("[a-f0-9]{56}")) == true) {
                    QueryDelegationsAndRewardsResult.serializer()
                } else if (element.keys.isEmpty()) {
                    EmptyQueryResult.serializer()
                } else {
                    throw IllegalStateException("No Serializer found to decode: $element")
                }
            }
            is JsonArray -> {
                StringArrayQueryResult.serializer()
            }
            is JsonPrimitive -> {
                LongQueryResult.serializer()
            }
            else -> throw IllegalStateException("No Serializer found to decode: $element")
        }
    }
}
