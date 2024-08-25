package io.newm.kogmios.protocols.model.serializers

import io.newm.kogmios.protocols.model.EraSummary
import io.newm.kogmios.protocols.model.result.EraSummariesResult
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object EraSummariesResultSerializer : KSerializer<EraSummariesResult> {
    private val delegateListSerializer = ListSerializer(EraSummary.serializer())

    override fun deserialize(decoder: Decoder): EraSummariesResult =
        EraSummariesResult().also {
            it.addAll(delegateListSerializer.deserialize(decoder))
        }

    override val descriptor: SerialDescriptor =
        SerialDescriptor("EraSummariesResult", delegateListSerializer.descriptor)

    override fun serialize(
        encoder: Encoder,
        value: EraSummariesResult
    ) {
        delegateListSerializer.serialize(encoder, value)
    }
}
