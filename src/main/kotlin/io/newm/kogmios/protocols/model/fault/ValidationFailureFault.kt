package io.newm.kogmios.protocols.model.fault

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Some of the (V1) scripts failed to evaluate to a positive outcome.
 */
@Serializable
@SerialName("3012")
data class ValidationFailureFault(
    @SerialName("code")
    override val code: Long,
    @SerialName("message")
    override val message: String,
    @SerialName("data")
    override val data: ValidationFailureFaultData,
) : Fault

@Serializable
data class ValidationFailureFaultData(
    @SerialName("validationError")
    val validationError: String,
    @SerialName("traces")
    val traces: List<String>,
) : FaultData
