package io.newm.kogmios.protocols.model.fault

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * The transaction references an unknown stake credential. For example, to delegate to a stake pool, you must first register the stake key or script used for delegation. This may be done in the same transaction or in an earlier transaction but cannot happen retro-actively. The field 'data.unknownCredential' indicates what credential is used without being registered.
 */
@Serializable
@SerialName("3146")
data class UnknownCredentialFault(
    @SerialName("code")
    override val code: Long,
    @SerialName("message")
    override val message: String,
    @SerialName("data")
    override val data: UnknownCredentialFaultData,
) : Fault

@Serializable
data class UnknownCredentialFaultData(
    @SerialName("unknownCredential")
    val unknownCredential: String,
) : FaultData
