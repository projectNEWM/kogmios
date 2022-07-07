package io.projectnewm.kogmios.protocols.model.serializers

import io.projectnewm.kogmios.protocols.model.PoolResult
import io.projectnewm.kogmios.protocols.model.QueryPoolParametersResult
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

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
