package io.newm.kogmios.protocols.model.fault

import io.newm.kogmios.protocols.model.ExecutionUnits
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * The transaction execution budget for scripts execution is above the allowed limit. The protocol limits the amount of execution that a single transaction can do. This limit is set by a protocol parameter. The field 'data.maximumExecutionUnits' indicates the current limit and the field 'data.providedExecutionUnits' indicates how much the transaction requires.
 */
@Serializable
@SerialName("3134")
data class ExecutionUnitsTooLargeFault(
    @SerialName("code")
    override val code: Long,
    @SerialName("message")
    override val message: String,
    @SerialName("data")
    override val data: ExecutionUnitsTooLargeFaultData,
) : Fault

@Serializable
data class ExecutionUnitsTooLargeFaultData(
    @SerialName("providedExecutionUnits")
    val providedExecutionUnits: ExecutionUnits,
    @SerialName("maximumExecutionUnits")
    val maximumExecutionUnits: ExecutionUnits,
) : FaultData
