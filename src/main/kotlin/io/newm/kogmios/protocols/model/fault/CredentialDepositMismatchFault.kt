package io.newm.kogmios.protocols.model.fault

import com.fasterxml.jackson.annotation.JsonProperty

import com.fasterxml.jackson.annotation.JsonTypeName

import io.newm.kogmios.protocols.model.Ada

/**
 * The deposit specified in a stake credential registration (for delegation or governance) does not match the current value set by protocol parameters. The field 'data.expectedDeposit', when present, indicates the deposit amount as currently expected by ledger.
 */

@JsonTypeName("3151")
data class CredentialDepositMismatchFault(
    @param:JsonProperty(value = "code", required = true)
    @get:JsonProperty("code")
    override val code: Long,
    @param:JsonProperty(value = "message", required = true)
    @get:JsonProperty("message")
    override val message: String,
    @param:JsonProperty(value = "data", required = true)
    @get:JsonProperty("data")
    override val data: CredentialDepositMismatchFaultData,
) : Fault

data class CredentialDepositMismatchFaultData(
    @param:JsonProperty(value = "providedDeposit", required = true)
    @get:JsonProperty("providedDeposit")
    val providedDeposit: Ada,
    @param:JsonProperty(value = "expectedDeposit", required = true)
    @get:JsonProperty("expectedDeposit")
    val expectedDeposit: Ada,
) : FaultData
