package io.newm.kogmios.protocols.model.fault

import com.fasterxml.jackson.annotation.JsonProperty

import com.fasterxml.jackson.annotation.JsonTypeName

import io.newm.kogmios.protocols.model.UtxoOutput

/**
 * Some output values in the transaction are too large. Once serialized, values must be below a certain threshold. That threshold sits around 4 KB during the Mary era, and was then made configurable as a protocol parameter in later era. The field 'data.excessivelyLargeOutputs' lists all transaction outputs with values that are above the limit.
 */

@JsonTypeName("3120")
data class ValueTooLargeFault(
    @param:JsonProperty(value = "code", required = true)
    @get:JsonProperty("code")
    override val code: Long,
    @param:JsonProperty(value = "message", required = true)
    @get:JsonProperty("message")
    override val message: String,
    @param:JsonProperty(value = "data", required = true)
    @get:JsonProperty("data")
    override val data: ValueTooLargeFaultData,
) : Fault

data class ValueTooLargeFaultData(
    @param:JsonProperty(value = "excessivelyLargeOutputs", required = true)
    @get:JsonProperty("excessivelyLargeOutputs")
    val excessivelyLargeOutputs: List<UtxoOutput>,
) : FaultData
