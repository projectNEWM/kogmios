package io.newm.kogmios.protocols.model.fault

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Returned when trying to evaluate execution units of an era that is now considered too old and is no longer supported. This can solved by using a more recent transaction format.
 */
@Serializable
@SerialName("3001")
data class UnsupportedEraFault(
    @SerialName("code")
    override val code: Long,
    @SerialName("message")
    override val message: String,
    @SerialName("data")
    override val data: UnsupportedEraFaultData,
) : Fault

@Serializable
data class UnsupportedEraFaultData(
    @SerialName("unsupportedEra")
    val unsupportedEra: String,
) : FaultData
