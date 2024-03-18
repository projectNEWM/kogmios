package io.newm.kogmios.protocols.model.fault

import io.newm.kogmios.protocols.model.Validator
import io.newm.kogmios.protocols.model.serializers.ScriptExecutionFailureFaultDataSerializer
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

@Serializable(with = ScriptExecutionFailureFaultDataSerializer::class)
class ScriptExecutionFailureFaultData : ArrayList<ScriptExecutionFailureFaultDataItem>(), FaultData {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ScriptExecutionFailureFaultData) return false
        if (!super.equals(other)) return false
        return true
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }
}

@Serializable
data class ScriptExecutionFailureFaultDataItem(
    @SerialName("validator")
    val validator: Validator,
    @SerialName("error")
    val error: Fault,
) : FaultData
