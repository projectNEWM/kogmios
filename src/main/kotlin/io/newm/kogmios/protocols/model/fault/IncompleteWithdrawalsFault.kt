package io.newm.kogmios.protocols.model.fault

import io.newm.kogmios.protocols.model.Ada
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * The transaction contains incomplete or invalid rewards withdrawals. When present, rewards withdrawals must consume rewards in full, there cannot be any leftover. The field 'data.incompleteWithdrawals' contains a map of withdrawals and their current rewards balance.
 */
@Serializable
@SerialName("3141")
data class IncompleteWithdrawalsFault(
    @SerialName("code")
    override val code: Long,
    @SerialName("message")
    override val message: String,
    @SerialName("data")
    override val data: IncompleteWithdrawalsFaultData,
) : Fault

@Serializable
data class IncompleteWithdrawalsFaultData(
    @SerialName("incompleteWithdrawals")
    val incompleteWithdrawals: Map<String, Ada>,
) : FaultData
