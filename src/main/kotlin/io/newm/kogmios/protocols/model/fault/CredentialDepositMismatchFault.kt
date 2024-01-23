package io.newm.kogmios.protocols.model.fault

import io.newm.kogmios.protocols.model.Ada
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * The deposit specified in a stake credential registration (for delegation or governance) does not match the current value set by protocol parameters. The field 'data.expectedDeposit', when present, indicates the deposit amount as currently expected by ledger.
 */
@Serializable
@SerialName("3151")
data class CredentialDepositMismatchFault(
    @SerialName("code")
    override val code: Long,
    @SerialName("message")
    override val message: String,
    @SerialName("data")
    override val data: CredentialDepositMismatchFaultData,
) : Fault

@Serializable
data class CredentialDepositMismatchFaultData(
    @SerialName("providedDeposit")
    val providedDeposit: Ada,
    @SerialName("expectedDeposit")
    val expectedDeposit: Ada,
) : FaultData
