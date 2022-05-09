package io.projectnewm.kogmios.protocols.localstatequery.serializers

import io.projectnewm.kogmios.protocols.localstatequery.model.*
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.json.*

object QueryResultSerializer : JsonContentPolymorphicSerializer<QueryResult>(QueryResult::class) {

    private val twentyEightByteHex = Regex("[a-f\\d]{56}")
    private val digitString = Regex("\\d+")

    override fun selectDeserializer(element: JsonElement): DeserializationStrategy<out QueryResult> {
        return when (element) {
            is JsonObject -> {
                if (("hash" in element) and ("slot" in element)) {
                    QueryPointResult.serializer()
                } else if ("minFeeCoefficient" in element) {
                    QueryCurrentProtocolParametersResult.serializer()
                } else if ("systemStart" in element) {
                    CompactGenesis.serializer()
                } else if (("time" in element) and ("epoch" in element) and ("slot" in element)) {
                    Bound.serializer()
                } else if (element.keys.firstOrNull()?.startsWith("pool1") == true) {
                    QueryPoolParametersResult.serializer()
                } else if (element.keys.firstOrNull()?.matches(twentyEightByteHex) == true) {
                    val firstElement = element[element.keys.first()]!!.jsonObject
                    if (firstElement.keys.firstOrNull()?.startsWith("stake") == true) {
                        QueryDelegationsAndRewardsResult.serializer()
                    } else {
                        // must start with "pool"
                        QueryNonMyopicMemberRewardsResult.serializer()
                    }
                } else if (element.keys.firstOrNull()?.matches(digitString) == true) {
                    QueryNonMyopicMemberRewardsResult.serializer()
                } else if (element.keys.isEmpty()) {
                    EmptyQueryResult.serializer()
                } else {
                    throw IllegalStateException("No Serializer found to decode: $element")
                }
            }
            is JsonArray -> {
                when (element[0]) {
                    is JsonObject -> EraSummariesQueryResult.serializer()
                    else -> StringArrayQueryResult.serializer()
                }
            }
            is JsonPrimitive -> {
                LongQueryResult.serializer()
            }
            else -> throw IllegalStateException("No Serializer found to decode: $element")
        }
    }
}
