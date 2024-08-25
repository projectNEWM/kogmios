package io.newm.kogmios.protocols.model.serializers

import io.newm.kogmios.protocols.model.PoolDistribution
import io.newm.kogmios.protocols.model.result.LiveStakeDistributionResult
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object QueryStakeDistributionResultSerializer : KSerializer<LiveStakeDistributionResult> {
    private val delegateMapSerializer = MapSerializer(String.serializer(), PoolDistribution.serializer())

    override fun deserialize(decoder: Decoder): LiveStakeDistributionResult =
        LiveStakeDistributionResult().also {
            it.putAll(delegateMapSerializer.deserialize(decoder))
        }

    override val descriptor: SerialDescriptor =
        SerialDescriptor("QueryStakeDistributionResult", delegateMapSerializer.descriptor)

    override fun serialize(
        encoder: Encoder,
        value: LiveStakeDistributionResult
    ) {
        delegateMapSerializer.serialize(encoder, value)
    }
}
