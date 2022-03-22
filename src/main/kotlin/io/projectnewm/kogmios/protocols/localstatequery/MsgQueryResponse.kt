package io.projectnewm.kogmios.protocols.localstatequery

import io.projectnewm.kogmios.protocols.model.PointDetail
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerialName
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject

/**
 * Response that comes back from Ogmios after a query message is sent.
 */
@kotlinx.serialization.Serializable
@SerialName("Query")
data class MsgQueryResponse(
    @SerialName("result")
    val result: QueryResult
) : JsonWspResponse()

@kotlinx.serialization.Serializable(with = QueryResultSerializer::class)
abstract class QueryResult

@kotlinx.serialization.Serializable
data class QueryPointDetail(
    val slot: Long,
    val hash: String,
) : QueryResult() {
    fun toPointDetail(): PointDetail = PointDetail(slot = this.slot, hash = this.hash)
}


object QueryResultSerializer : JsonContentPolymorphicSerializer<QueryResult>(QueryResult::class) {
    override fun selectDeserializer(element: JsonElement): DeserializationStrategy<out QueryResult> {
        return if ((element as? JsonObject)?.let { "slot" in it } == true) {
            QueryPointDetail.serializer()
        } else {
            QueryResult.serializer()
        }
    }

}