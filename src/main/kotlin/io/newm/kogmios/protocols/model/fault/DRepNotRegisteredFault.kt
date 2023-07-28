package io.newm.kogmios.protocols.model.fault

import io.newm.kogmios.protocols.model.DelegateRepresentative
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * The transaction references an unknown delegate representative. To delegate to a representative, it must first register as such. This may be done in the same transaction or in an earlier transaction but cannot happen retro-actively. The field 'data.unknownDelegateRepresentative' indicates what credential is used without being registered.
 */
@Serializable
@SerialName("3153")
data class DRepNotRegisteredFault(
    @SerialName("code")
    override val code: Long,
    @SerialName("message")
    override val message: String,
    @SerialName("data")
    override val data: DRepNotRegisteredFaultData,
) : Fault

@Serializable
data class DRepNotRegisteredFaultData(
    @SerialName("unknownDelegateRepresentative")
    val unknownDelegateRepresentative: DelegateRepresentative,
) : FaultData
