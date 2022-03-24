package io.projectnewm.kogmios.protocols.localstatequery

import io.projectnewm.kogmios.protocols.model.PointDetail
import kotlinx.serialization.Contextual
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import java.math.BigDecimal
import java.math.BigInteger

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
interface QueryResult

@kotlinx.serialization.Serializable
class EmptyQueryResult : QueryResult

@kotlinx.serialization.Serializable
data class QueryPointResult(
    @SerialName("slot")
    val slot: Long,
    @SerialName("hash")
    val hash: String,
) : QueryResult {
    fun toPointDetail(): PointDetail = PointDetail(slot = this.slot, hash = this.hash)
}

@kotlinx.serialization.Serializable(with = QueryPoolParametersResultSerializer::class)
class QueryPoolParametersResult : LinkedHashMap<String, PoolResult>(), QueryResult

@kotlinx.serialization.Serializable
data class PoolResult(
    @SerialName("id")
    val id: String,
    @SerialName("vrf")
    val vrf: String,
    @SerialName("pledge")
    @Contextual
    val pledge: BigInteger,
    @SerialName("cost")
    @Contextual
    val cost: BigInteger,
    @SerialName("margin")
    @Contextual
    val margin: BigDecimal,
    @SerialName("rewardAccount")
    val rewardAccount: String,
    @SerialName("owners")
    val owners: List<String>,
    @SerialName("relays")
    val relays: List<RelayResult>?,
    @SerialName("metadata")
    val metadata: MetadataResult,
)

@kotlinx.serialization.Serializable
data class RelayResult(
    @SerialName("hostname")
    val hostname: String? = null,
    @SerialName("port")
    val port: Int? = null,
    @SerialName("ipv4")
    val ipv4: String? = null,
    @SerialName("ipv6")
    val ipv6: String? = null,
)

@kotlinx.serialization.Serializable
data class MetadataResult(
    @SerialName("url")
    val url: String,
    @SerialName("hash")
    val hash: String,
)

object QueryResultSerializer : JsonContentPolymorphicSerializer<QueryResult>(QueryResult::class) {
    override fun selectDeserializer(element: JsonElement): DeserializationStrategy<out QueryResult> {
        return if ((element as? JsonObject)?.let { "slot" in it } == true) {
            QueryPointResult.serializer()
        } else if ((element as? JsonObject)?.keys?.firstOrNull()?.startsWith("pool1") == true) {
            QueryPoolParametersResult.serializer()
        } else if ((element as? JsonObject)?.keys?.size == 0) {
            EmptyQueryResult.serializer()
        } else {
            throw IllegalStateException("No Serializer found to decode: $element")
        }
    }
}

object QueryPoolParametersResultSerializer : KSerializer<QueryPoolParametersResult> {
    private val delegateMapSerializer = MapSerializer(String.serializer(), PoolResult.serializer())
    override fun deserialize(decoder: Decoder): QueryPoolParametersResult {
        return QueryPoolParametersResult().also {
            it.putAll(delegateMapSerializer.deserialize(decoder))
        }
    }

    override val descriptor: SerialDescriptor =
        SerialDescriptor("QueryPoolParametersResult", delegateMapSerializer.descriptor)

    override fun serialize(encoder: Encoder, value: QueryPoolParametersResult) {
        delegateMapSerializer.serialize(encoder, value)
    }
}
