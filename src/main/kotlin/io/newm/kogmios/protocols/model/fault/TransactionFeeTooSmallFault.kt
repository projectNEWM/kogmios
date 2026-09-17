package io.newm.kogmios.protocols.model.fault

import com.fasterxml.jackson.annotation.JsonProperty

import com.fasterxml.jackson.annotation.JsonTypeName

import io.newm.kogmios.protocols.model.Ada

/**
 * Insufficient fee! The transaction doesn't not contain enough fee to cover the minimum required by the protocol. Note that fee depends on (a) a flat cost fixed by the protocol, (b) the size of the serialized transaction, (c) the budget allocated for Plutus script execution. The field 'data.minimumRequiredFee' indicates the minimum required fee whereas 'data.providedFee' refers to the fee currently supplied with the transaction.
 */

@JsonTypeName("3122")
data class TransactionFeeTooSmallFault(
    @param:JsonProperty(value = "code", required = true)
    @get:JsonProperty("code")
    override val code: Long,
    @param:JsonProperty(value = "message", required = true)
    @get:JsonProperty("message")
    override val message: String,
    @param:JsonProperty(value = "data", required = true)
    @get:JsonProperty("data")
    override val data: TransactionFeeTooSmallFaultData,
) : Fault

data class TransactionFeeTooSmallFaultData(
    @param:JsonProperty(value = "minimumRequiredFee", required = true)
    @get:JsonProperty("minimumRequiredFee")
    val minimumRequiredFee: Ada,
    @param:JsonProperty(value = "providedFee", required = true)
    @get:JsonProperty("providedFee")
    val providedFee: Ada,
) : FaultData
