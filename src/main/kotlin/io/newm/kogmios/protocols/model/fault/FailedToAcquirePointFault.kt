package io.newm.kogmios.protocols.model.fault

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Unable to acquire the ledger state at the request point.
 */
@Serializable
@SerialName("2000")
data class FailedToAcquirePointFault(
    @SerialName("code")
    override val code: Long,
    @SerialName("message")
    override val message: String,
    @SerialName("data")
    override val data: StringFaultData,
) : Fault
