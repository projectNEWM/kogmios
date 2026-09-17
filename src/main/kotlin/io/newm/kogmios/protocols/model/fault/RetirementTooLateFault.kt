package io.newm.kogmios.protocols.model.fault

import com.fasterxml.jackson.annotation.JsonProperty

import com.fasterxml.jackson.annotation.JsonTypeName

/**
 * A stake pool retirement certificate is trying to retire too late in the future. Indeed, there's a maximum delay for stake pool retirement, controlled by a protocol parameter. The field 'data.currentEpoch' indicates the current epoch known of the ledger, 'data.declaredEpoch' refers to the epoch declared in the retirement certificate and 'data.firstInvalidEpoch' is the first epoch considered invalid (too far) for retirement
 */

@JsonTypeName("3142")
data class RetirementTooLateFault(
    @param:JsonProperty(value = "code", required = true)
    @get:JsonProperty("code")
    override val code: Long,
    @param:JsonProperty(value = "message", required = true)
    @get:JsonProperty("message")
    override val message: String,
    @param:JsonProperty(value = "data", required = true)
    @get:JsonProperty("data")
    override val data: RetirementTooLateFaultData,
) : Fault

data class RetirementTooLateFaultData(
    @param:JsonProperty(value = "currentEpoch", required = true)
    @get:JsonProperty("currentEpoch")
    val currentEpoch: Long,
    @param:JsonProperty(value = "declaredEpoch", required = true)
    @get:JsonProperty("declaredEpoch")
    val declaredEpoch: Long,
    @param:JsonProperty(value = "firstInvalidEpoch", required = true)
    @get:JsonProperty("firstInvalidEpoch")
    val firstInvalidEpoch: Long,
) : FaultData
