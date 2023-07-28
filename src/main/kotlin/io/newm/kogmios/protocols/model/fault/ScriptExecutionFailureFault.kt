package io.newm.kogmios.protocols.model.fault

import io.newm.kogmios.protocols.model.Validator
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * One or more script execution terminated with an error.
 */
@Serializable
@SerialName("3010")
data class ScriptExecutionFailureFault(
    @SerialName("code")
    override val code: Long,
    @SerialName("message")
    override val message: String,
    @SerialName("data")
    override val data: ScriptExecutionFailureFaultData,
) : Fault

@Serializable
data class ScriptExecutionFailureFaultData(
    @SerialName("validator")
    val validator: Validator,
    @SerialName("error")
    val error: Fault,
) : FaultData
