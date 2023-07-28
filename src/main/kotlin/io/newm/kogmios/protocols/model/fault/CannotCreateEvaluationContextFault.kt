package io.newm.kogmios.protocols.model.fault

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * The transaction is malformed or missing information; making evaluation impossible.
 */
@Serializable
@SerialName("3004")
data class CannotCreateEvaluationContextFault(
    override val code: Long,
    override val message: String,
    override val data: CannotCreateEvaluationContextFaultData,
) : Fault

@Serializable
data class CannotCreateEvaluationContextFaultData(
    val reason: String,
) : FaultData
