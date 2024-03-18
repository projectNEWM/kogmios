package io.newm.kogmios.protocols.model.serializers

import io.newm.kogmios.protocols.model.fault.ScriptExecutionFailureFaultData
import io.newm.kogmios.protocols.model.fault.ScriptExecutionFailureFaultDataItem
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object ScriptExecutionFailureFaultDataSerializer : KSerializer<ScriptExecutionFailureFaultData> {
    private val delegateListSerializer = ListSerializer(ScriptExecutionFailureFaultDataItem.serializer())

    override fun deserialize(decoder: Decoder): ScriptExecutionFailureFaultData {
        return ScriptExecutionFailureFaultData().also {
            it.addAll(delegateListSerializer.deserialize(decoder))
        }
    }

    override val descriptor: SerialDescriptor =
        SerialDescriptor("EraSummariesResult", delegateListSerializer.descriptor)

    override fun serialize(
        encoder: Encoder,
        value: ScriptExecutionFailureFaultData
    ) {
        delegateListSerializer.serialize(encoder, value)
    }
}
