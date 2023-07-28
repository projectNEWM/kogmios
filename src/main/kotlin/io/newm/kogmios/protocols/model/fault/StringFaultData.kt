package io.newm.kogmios.protocols.model.fault

import io.newm.kogmios.protocols.model.serializers.StringFaultDataSerializer
import kotlinx.serialization.Serializable

@Serializable(with = StringFaultDataSerializer::class)
data class StringFaultData(
    val value: String,
) : FaultData
