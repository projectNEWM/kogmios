package io.newm.kogmios.protocols.model.serializers

import io.newm.kogmios.protocols.model.result.UtxoResult
import io.newm.kogmios.protocols.model.result.UtxoResultItem
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object UtxoResultSerializer : KSerializer<UtxoResult> {
    private val delegateListSerializer = ListSerializer(UtxoResultItem.serializer())

    override fun deserialize(decoder: Decoder): UtxoResult {
        return UtxoResult().also {
            it.addAll(delegateListSerializer.deserialize(decoder))
        }
    }

    override val descriptor: SerialDescriptor =
        SerialDescriptor("UtxoResult", delegateListSerializer.descriptor)

    override fun serialize(
        encoder: Encoder,
        value: UtxoResult
    ) {
        delegateListSerializer.serialize(encoder, value)
    }
}
