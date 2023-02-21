package io.newm.kogmios.protocols.model.serializers

import io.newm.kogmios.protocols.model.Bound
import io.newm.kogmios.protocols.model.CompactGenesisAlonzo
import io.newm.kogmios.protocols.model.CompactGenesisByron
import io.newm.kogmios.protocols.model.CompactGenesisShelley
import io.newm.kogmios.protocols.model.EmptyQueryResult
import io.newm.kogmios.protocols.model.EraSummariesQueryResult
import io.newm.kogmios.protocols.model.InstantQueryResult
import io.newm.kogmios.protocols.model.LongQueryResult
import io.newm.kogmios.protocols.model.QueryCurrentProtocolParametersResult
import io.newm.kogmios.protocols.model.QueryDelegationsAndRewardsResult
import io.newm.kogmios.protocols.model.QueryFutureProtocolParametersResult
import io.newm.kogmios.protocols.model.QueryNonMyopicMemberRewardsResult
import io.newm.kogmios.protocols.model.QueryPointResult
import io.newm.kogmios.protocols.model.QueryPoolParametersResult
import io.newm.kogmios.protocols.model.QueryResult
import io.newm.kogmios.protocols.model.QueryStakeDistributionResult
import io.newm.kogmios.protocols.model.StringArrayQueryResult
import io.newm.kogmios.protocols.model.UtxoByTxInQueryResult
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject

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
                } else if ("genesisKeyHashes" in element) {
                    CompactGenesisByron.serializer()
                } else if ("maxExecutionUnitsPerTransaction" in element) {
                    CompactGenesisAlonzo.serializer()
                } else if ("systemStart" in element) {
                    CompactGenesisShelley.serializer()
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
