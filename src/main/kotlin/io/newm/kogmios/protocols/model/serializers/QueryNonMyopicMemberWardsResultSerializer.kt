package io.projectnewm.kogmios.protocols.model.serializers

import io.projectnewm.kogmios.protocols.model.NonMyopicMemberRewardsResult
import io.projectnewm.kogmios.protocols.model.QueryNonMyopicMemberRewardsResult
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object QueryNonMyopicMemberWardsResultSerializer : KSerializer<QueryNonMyopicMemberRewardsResult> {
    private val delegateMapSerializer = MapSerializer(String.serializer(), NonMyopicMemberRewardsResult.serializer())
    override fun deserialize(decoder: Decoder): QueryNonMyopicMemberRewardsResult {
        return QueryNonMyopicMemberRewardsResult().also {
            it.putAll(delegateMapSerializer.deserialize(decoder))
        }
    }

    override val descriptor: SerialDescriptor =
        SerialDescriptor("QueryNonMyopicMemberRewardsResult", delegateMapSerializer.descriptor)

    override fun serialize(encoder: Encoder, value: QueryNonMyopicMemberRewardsResult) {
        delegateMapSerializer.serialize(encoder, value)
    }
}
