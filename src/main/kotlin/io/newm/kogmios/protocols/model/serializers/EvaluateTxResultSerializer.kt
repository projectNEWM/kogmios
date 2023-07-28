package io.newm.kogmios.protocols.model.serializers

import io.newm.kogmios.protocols.model.result.EvaluateTx
import io.newm.kogmios.protocols.model.result.EvaluateTxResult
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object EvaluateTxResultSerializer : KSerializer<EvaluateTxResult> {
    private val delegateListSerializer = ListSerializer(EvaluateTx.serializer())

    override fun deserialize(decoder: Decoder): EvaluateTxResult {
        return EvaluateTxResult().also {
            it.addAll(delegateListSerializer.deserialize(decoder))
        }
    }

    override val descriptor: SerialDescriptor =
        SerialDescriptor("EvaluateTxResult", delegateListSerializer.descriptor)

    override fun serialize(
        encoder: Encoder,
        value: EvaluateTxResult
    ) {
        delegateListSerializer.serialize(encoder, value)
    }
}
