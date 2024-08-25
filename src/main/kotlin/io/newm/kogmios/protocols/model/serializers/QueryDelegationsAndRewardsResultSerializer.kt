package io.newm.kogmios.protocols.model.serializers

import io.newm.kogmios.protocols.model.RewardAccountSummary
import io.newm.kogmios.protocols.model.result.RewardAccountSummariesResult
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object QueryDelegationsAndRewardsResultSerializer : KSerializer<RewardAccountSummariesResult> {
    private val delegateMapSerializer = MapSerializer(String.serializer(), RewardAccountSummary.serializer())

    override fun deserialize(decoder: Decoder): RewardAccountSummariesResult =
        RewardAccountSummariesResult().also {
            it.putAll(delegateMapSerializer.deserialize(decoder))
        }

    override val descriptor: SerialDescriptor =
        SerialDescriptor("QueryDelegationsAndRewardsResult", delegateMapSerializer.descriptor)

    override fun serialize(
        encoder: Encoder,
        value: RewardAccountSummariesResult
    ) {
        delegateMapSerializer.serialize(encoder, value)
    }
}
