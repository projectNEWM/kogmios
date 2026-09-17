package io.newm.kogmios.protocols.model.fault

import com.fasterxml.jackson.annotation.JsonProperty

import com.fasterxml.jackson.annotation.JsonTypeName

import io.newm.kogmios.protocols.model.Ada

/**
 * There's a mismatch between the proposal deposit amount declared in the transaction and the one expected by the ledger. The deposit is actually configured by a protocol parameter. The field 'data.expectedDeposit' indicates the current configuration and amount expected by the ledger. The field 'data.providedDeposit' is a reminder of the what was set in the submitted transaction.
 */

@JsonTypeName("3155")
data class GovernanceProposalDepositMismatchFault(
    @param:JsonProperty(value = "code", required = true)
    @get:JsonProperty("code")
    override val code: Long,
    @param:JsonProperty(value = "message", required = true)
    @get:JsonProperty("message")
    override val message: String,
    @param:JsonProperty(value = "data", required = true)
    @get:JsonProperty("data")
    override val data: GovernanceProposalDepositMismatchFaultData,
) : Fault

data class GovernanceProposalDepositMismatchFaultData(
    @param:JsonProperty(value = "providedDeposit", required = true)
    @get:JsonProperty("providedDeposit")
    val providedDeposit: Ada,
    @param:JsonProperty(value = "expectedDeposit", required = true)
    @get:JsonProperty("expectedDeposit")
    val expectedDeposit: Ada,
) : FaultData
