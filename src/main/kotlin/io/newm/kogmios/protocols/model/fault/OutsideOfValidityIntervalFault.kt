package io.newm.kogmios.protocols.model.fault

import com.fasterxml.jackson.annotation.JsonProperty

import com.fasterxml.jackson.annotation.JsonTypeName

import io.newm.kogmios.protocols.model.ValidityInterval

/**
 * The transaction is outside of its validity interval. It was either submitted too early or too late. A transaction that has a lower validity bound can only be accepted by the ledger (and make it to the mempool) if the ledger's current slot is greater than the specified bound. The upper bound works similarly, as a time to live. The field 'data.currentSlot' contains the current slot as known of the ledger (this may be different from the current network slot if the ledger is still catching up). The field 'data.validityInterval' is a reminder of the validity interval provided with the transaction.
 */

@JsonTypeName("3118")
data class OutsideOfValidityIntervalFault(
    @param:JsonProperty(value = "code", required = true)
    @get:JsonProperty("code")
    override val code: Long,
    @param:JsonProperty(value = "message", required = true)
    @get:JsonProperty("message")
    override val message: String,
    @param:JsonProperty(value = "data", required = true)
    @get:JsonProperty("data")
    override val data: OutsideOfValidityIntervalFaultData,
) : Fault

data class OutsideOfValidityIntervalFaultData(
    @param:JsonProperty(value = "validityInterval", required = true)
    @get:JsonProperty("validityInterval")
    val validityInterval: ValidityInterval,
    @param:JsonProperty(value = "currentSlot", required = true)
    @get:JsonProperty("currentSlot")
    val currentSlot: Long,
) : FaultData
