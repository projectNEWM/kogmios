package io.newm.kogmios.protocols.model.fault

import com.fasterxml.jackson.annotation.JsonProperty

import com.fasterxml.jackson.annotation.JsonTypeName

import io.newm.kogmios.protocols.model.Validator

/**
 * One or more script execution terminated with an error.
 */

@JsonTypeName("3010")
data class ScriptExecutionFailureFault(
    @param:JsonProperty(value = "code", required = true)
    @get:JsonProperty("code")
    override val code: Long,
    @param:JsonProperty(value = "message", required = true)
    @get:JsonProperty("message")
    override val message: String,
    @param:JsonProperty(value = "data", required = true)
    @get:JsonProperty("data")
    override val data: ScriptExecutionFailureFaultData,
) : Fault

class ScriptExecutionFailureFaultData :
    ArrayList<ScriptExecutionFailureFaultDataItem>(),
    FaultData {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ScriptExecutionFailureFaultData) return false
        if (!super.equals(other)) return false
        return true
    }

    override fun hashCode(): Int = super.hashCode()
}

data class ScriptExecutionFailureFaultDataItem(
    @param:JsonProperty(value = "validator", required = true)
    @get:JsonProperty("validator")
    val validator: Validator,
    @param:JsonProperty(value = "error", required = true)
    @get:JsonProperty("error")
    val error: Fault,
) : FaultData
