package io.newm.kogmios.protocols.model.serializers

import io.newm.kogmios.protocols.model.ProposedProtocolParameters
import io.newm.kogmios.protocols.model.result.ProposedProtocolParametersResult
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object ProposedProtocolParametersResultSerializer : KSerializer<ProposedProtocolParametersResult> {
    private val delegateListSerializer = ListSerializer(ProposedProtocolParameters.serializer())

    override fun deserialize(decoder: Decoder): ProposedProtocolParametersResult {
        return ProposedProtocolParametersResult().also {
            it.addAll(delegateListSerializer.deserialize(decoder))
        }
    }

    override val descriptor: SerialDescriptor =
        SerialDescriptor("ProposedProtocolParametersResult", delegateListSerializer.descriptor)

    override fun serialize(
        encoder: Encoder,
        value: ProposedProtocolParametersResult
    ) {
        delegateListSerializer.serialize(encoder, value)
    }
}
