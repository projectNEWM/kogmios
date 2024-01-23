package io.newm.kogmios.protocols.model.fault

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Trying to re-register some already known credentials. Stake credentials can only be registered once. This is true for both keys and scripts. The field 'data.knownCredential' points to an already known credential that's being re-registered by this transaction.
 */
@Serializable
@SerialName("3145")
data class CredentialAlreadyRegisteredFault(
    @SerialName("code")
    override val code: Long,
    @SerialName("message")
    override val message: String,
    @SerialName("data")
    override val data: CredentialAlreadyRegisteredFaultData,
) : Fault

@Serializable
data class CredentialAlreadyRegisteredFaultData(
    @SerialName("knownCredential")
    val knownCredential: String,
) : FaultData
