package io.newm.kogmios.protocols.model.fault

import com.fasterxml.jackson.annotation.JsonProperty

import com.fasterxml.jackson.annotation.JsonTypeName

import io.newm.kogmios.protocols.model.ExecutionUnits

/**
 * The transaction execution budget for scripts execution is above the allowed limit. The protocol limits the amount of execution that a single transaction can do. This limit is set by a protocol parameter. The field 'data.maximumExecutionUnits' indicates the current limit and the field 'data.providedExecutionUnits' indicates how much the transaction requires.
 */

@JsonTypeName("3134")
data class ExecutionUnitsTooLargeFault(
    @param:JsonProperty(value = "code", required = true)
    @get:JsonProperty("code")
    override val code: Long,
    @param:JsonProperty(value = "message", required = true)
    @get:JsonProperty("message")
    override val message: String,
    @param:JsonProperty(value = "data", required = true)
    @get:JsonProperty("data")
    override val data: ExecutionUnitsTooLargeFaultData,
) : Fault

data class ExecutionUnitsTooLargeFaultData(
    @param:JsonProperty(value = "providedExecutionUnits", required = true)
    @get:JsonProperty("providedExecutionUnits")
    val providedExecutionUnits: ExecutionUnits,
    @param:JsonProperty(value = "maximumExecutionUnits", required = true)
    @get:JsonProperty("maximumExecutionUnits")
    val maximumExecutionUnits: ExecutionUnits,
) : FaultData
