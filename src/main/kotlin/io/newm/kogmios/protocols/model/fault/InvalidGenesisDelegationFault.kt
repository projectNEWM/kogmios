package io.newm.kogmios.protocols.model.fault

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Invalid or unauthorized genesis delegation. The genesis delegate is unknown, invalid or already in use.
 */
@Serializable
@SerialName("3148")
data class InvalidGenesisDelegationFault(
    @SerialName("code")
    override val code: Long,
    @SerialName("message")
    override val message: String,
    @SerialName("data")
    override val data: FaultData? = null,
) : Fault
