package io.newm.kogmios.protocols.model.serializers

import io.newm.kogmios.protocols.model.NonMyopicMemberRewardsResult
import io.newm.kogmios.protocols.model.result.ProjectedRewardsResult
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object ProjectedRewardsResultSerializer : KSerializer<ProjectedRewardsResult> {
    private val delegateMapSerializer = MapSerializer(String.serializer(), NonMyopicMemberRewardsResult.serializer())

    override fun deserialize(decoder: Decoder): ProjectedRewardsResult =
        ProjectedRewardsResult().also {
            it.putAll(delegateMapSerializer.deserialize(decoder))
        }

    override val descriptor: SerialDescriptor =
        SerialDescriptor("ProjectedRewardsResult", delegateMapSerializer.descriptor)

    override fun serialize(
        encoder: Encoder,
        value: ProjectedRewardsResult
    ) {
        delegateMapSerializer.serialize(encoder, value)
    }
}
