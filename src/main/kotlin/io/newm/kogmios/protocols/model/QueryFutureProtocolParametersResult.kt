package io.newm.kogmios.protocols.model

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(with = QueryFutureProtocolParametersResultSerializer::class)
class QueryFutureProtocolParametersResult : LinkedHashMap<String, UpdateProposal>(), QueryResult

object QueryFutureProtocolParametersResultSerializer : KSerializer<QueryFutureProtocolParametersResult> {
    private val delegateMapSerializer = MapSerializer(String.serializer(), UpdateProposal.serializer())

    override fun deserialize(decoder: Decoder): QueryFutureProtocolParametersResult {
        return QueryFutureProtocolParametersResult().also {
            it.putAll(delegateMapSerializer.deserialize(decoder))
        }
    }

    override val descriptor: SerialDescriptor =
        SerialDescriptor("QueryFutureProtocolParametersResult", delegateMapSerializer.descriptor)

    override fun serialize(encoder: Encoder, value: QueryFutureProtocolParametersResult) {
        delegateMapSerializer.serialize(encoder, value)
    }
}
