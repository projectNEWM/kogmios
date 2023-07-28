package io.newm.kogmios.protocols.model.fault

import io.newm.kogmios.protocols.model.UtxoOutput
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Some output values in the transaction are too large. Once serialized, values must be below a certain threshold. That threshold sits around 4 KB during the Mary era, and was then made configurable as a protocol parameter in later era. The field 'data.excessivelyLargeOutputs' lists all transaction outputs with values that are above the limit.
 */
@Serializable
@SerialName("3120")
data class ValueTooLargeFault(
    @SerialName("code")
    override val code: Long,
    @SerialName("message")
    override val message: String,
    @SerialName("data")
    override val data: ValueTooLargeFaultData,
) : Fault

@Serializable
data class ValueTooLargeFaultData(
    @SerialName("excessivelyLargeOutputs")
    val excessivelyLargeOutputs: List<UtxoOutput>,
) : FaultData
