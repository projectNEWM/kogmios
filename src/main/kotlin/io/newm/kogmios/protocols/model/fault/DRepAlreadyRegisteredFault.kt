package io.newm.kogmios.protocols.model.fault

import io.newm.kogmios.protocols.model.DelegateRepresentative
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Trying to re-register some already known delegate representative. Delegate representatives can only be registered once. The field 'data.knownDelegateRepresentatives' points to an already known credential that's being re-registered by this transaction.
 */
@Serializable
@SerialName("3152")
data class DRepAlreadyRegisteredFault(
    @SerialName("code")
    override val code: Long,
    @SerialName("message")
    override val message: String,
    @SerialName("data")
    override val data: DRepAlreadyRegisteredFaultData,
) : Fault

@Serializable
data class DRepAlreadyRegisteredFaultData(
    @SerialName("knownDelegateRepresentative")
    val knownDelegateRepresentative: DelegateRepresentative,
) : FaultData
