package io.newm.kogmios.protocols.model.serializers

import io.newm.kogmios.protocols.model.DelegationsAndRewardsResult
import io.newm.kogmios.protocols.model.QueryDelegationsAndRewardsResult
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object QueryDelegationsAndRewardsResultSerializer : KSerializer<QueryDelegationsAndRewardsResult> {
    private val delegateMapSerializer = MapSerializer(String.serializer(), DelegationsAndRewardsResult.serializer())
    override fun deserialize(decoder: Decoder): QueryDelegationsAndRewardsResult {
        return QueryDelegationsAndRewardsResult().also {
            it.putAll(delegateMapSerializer.deserialize(decoder))
        }
    }

    override val descriptor: SerialDescriptor =
        SerialDescriptor("QueryDelegationsAndRewardsResult", delegateMapSerializer.descriptor)

    override fun serialize(encoder: Encoder, value: QueryDelegationsAndRewardsResult) {
        delegateMapSerializer.serialize(encoder, value)
    }
}
