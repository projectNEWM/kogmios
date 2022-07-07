package io.projectnewm.kogmios.protocols.model.serializers

import io.projectnewm.kogmios.protocols.model.NonMyopicMemberRewardsResult
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object NonMyopicMemberRewardsResultSerializer : KSerializer<NonMyopicMemberRewardsResult> {
    private val delegateMapSerializer = MapSerializer(String.serializer(), Long.serializer())
    override fun deserialize(decoder: Decoder): NonMyopicMemberRewardsResult {
        return NonMyopicMemberRewardsResult().also {
            it.putAll(delegateMapSerializer.deserialize(decoder))
        }
    }

    override val descriptor: SerialDescriptor =
        SerialDescriptor("NonMyopicMemberRewardsResult", delegateMapSerializer.descriptor)

    override fun serialize(encoder: Encoder, value: NonMyopicMemberRewardsResult) {
        delegateMapSerializer.serialize(encoder, value)
    }
}
