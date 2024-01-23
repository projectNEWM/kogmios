package io.newm.kogmios.protocols.model.fault

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * The transaction references an unknown stake pool as a target for delegation or update. Double-check the pool id mentioned in 'data.unknownStakePool'. Note also that order in which transactions are submitted matters; if you're trying to register a pool and delegate to it in one go, make sure to submit transactions in the right order.
 */
@Serializable
@SerialName("3140")
data class UnknownStakePoolFault(
    @SerialName("code")
    override val code: Long,
    @SerialName("message")
    override val message: String,
    @SerialName("data")
    override val data: UnknownStakePoolFaultData,
) : Fault

@Serializable
data class UnknownStakePoolFaultData(
    @SerialName("unknownStakePool")
    val unknownStakePool: String,
) : FaultData
