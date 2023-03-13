package io.newm.kogmios.protocols.model.serializers

import io.newm.kogmios.protocols.model.*
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.json.*

object QueryResultSerializer : JsonContentPolymorphicSerializer<QueryResult>(QueryResult::class) {

    private val twentyEightByteHex = Regex("[a-f\\d]{56}")
    private val digitString = Regex("\\d+")

    override fun selectDeserializer(element: JsonElement): DeserializationStrategy<QueryResult> {
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
                    val firstElement = element[element.keys.first()]!!.jsonObject
                    if ("stake" in firstElement) {
                        QueryStakeDistributionResult.serializer()
                    } else {
                        QueryPoolParametersResult.serializer()
                    }
                } else if (element.keys.firstOrNull()?.matches(twentyEightByteHex) == true) {
                    val firstElement = element[element.keys.first()]!!.jsonObject
                    if ("minFeeCoefficient" in firstElement) {
                        QueryFutureProtocolParametersResult.serializer()
                    } else if ("delegate" in firstElement) {
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
                if (element.jsonArray.size == 0) {
                    UtxoByTxInQueryResult.serializer()
                } else {
                    when (element[0]) {
                        is JsonObject -> EraSummariesQueryResult.serializer()
                        is JsonArray -> UtxoByTxInQueryResult.serializer()
                        else -> StringArrayQueryResult.serializer()
                    }
                }
            }
            is JsonPrimitive -> {
                if (element.isString) {
                    InstantQueryResult.serializer()
                } else {
                    LongQueryResult.serializer()
                }
            }
            else -> throw IllegalStateException("No Serializer found to decode: $element")
        }
    }
}
