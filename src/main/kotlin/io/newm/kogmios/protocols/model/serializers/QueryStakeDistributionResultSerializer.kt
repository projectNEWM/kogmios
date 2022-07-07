package io.newm.kogmios.protocols.model.serializers

import io.newm.kogmios.protocols.model.PoolDistribution
import io.newm.kogmios.protocols.model.QueryStakeDistributionResult
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object QueryStakeDistributionResultSerializer : KSerializer<QueryStakeDistributionResult> {
    private val delegateMapSerializer = MapSerializer(String.serializer(), PoolDistribution.serializer())
    override fun deserialize(decoder: Decoder): QueryStakeDistributionResult {
        return QueryStakeDistributionResult().also {
            it.putAll(delegateMapSerializer.deserialize(decoder))
        }
    }

    override val descriptor: SerialDescriptor =
        SerialDescriptor("QueryStakeDistributionResult", delegateMapSerializer.descriptor)

    override fun serialize(encoder: Encoder, value: QueryStakeDistributionResult) {
        delegateMapSerializer.serialize(encoder, value)
    }
}
