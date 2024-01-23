package io.newm.kogmios.protocols.model.serializers

import io.newm.kogmios.protocols.model.PoolResult
import io.newm.kogmios.protocols.model.result.StakePoolsResult
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object StakePoolsResultSerializer : KSerializer<StakePoolsResult> {
    private val delegateMapSerializer = MapSerializer(String.serializer(), PoolResult.serializer())

    override fun deserialize(decoder: Decoder): StakePoolsResult {
        return StakePoolsResult().also {
            it.putAll(delegateMapSerializer.deserialize(decoder))
        }
    }

    override val descriptor: SerialDescriptor =
        SerialDescriptor("StakePoolsResult", delegateMapSerializer.descriptor)

    override fun serialize(
        encoder: Encoder,
        value: StakePoolsResult
    ) {
        delegateMapSerializer.serialize(encoder, value)
    }
}
