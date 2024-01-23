package io.newm.kogmios.protocols.model.fault

import io.newm.kogmios.protocols.model.Ada
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * There's a mismatch between the proposal deposit amount declared in the transaction and the one expected by the ledger. The deposit is actually configured by a protocol parameter. The field 'data.expectedDeposit' indicates the current configuration and amount expected by the ledger. The field 'data.providedDeposit' is a reminder of the what was set in the submitted transaction.
 */
@Serializable
@SerialName("3155")
data class GovernanceProposalDepositMismatchFault(
    @SerialName("code")
    override val code: Long,
    @SerialName("message")
    override val message: String,
    @SerialName("data")
    override val data: GovernanceProposalDepositMismatchFaultData,
) : Fault

@Serializable
data class GovernanceProposalDepositMismatchFaultData(
    @SerialName("providedDeposit")
    val providedDeposit: Ada,
    @SerialName("expectedDeposit")
    val expectedDeposit: Ada,
) : FaultData
