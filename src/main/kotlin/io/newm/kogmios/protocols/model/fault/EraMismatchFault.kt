package io.newm.kogmios.protocols.model.fault

import com.fasterxml.jackson.annotation.JsonProperty

import com.fasterxml.jackson.annotation.JsonTypeName

/**
 * The era of the transaction does not match the era of the ledger.
 */

@JsonTypeName("3005")
data class EraMismatchFault(
    @param:JsonProperty(value = "code", required = true)
    @get:JsonProperty("code")
    override val code: Long,
    @param:JsonProperty(value = "message", required = true)
    @get:JsonProperty("message")
    override val message: String,
    @param:JsonProperty(value = "data", required = true)
    @get:JsonProperty("data")
    override val data: EraMismatchFaultData,
) : Fault

data class EraMismatchFaultData(
    @param:JsonProperty(value = "queryEra", required = true)
    @get:JsonProperty("queryEra")
    val queryEra: String,
    @param:JsonProperty(value = "ledgerEra", required = true)
    @get:JsonProperty("ledgerEra")
    val ledgerEra: String,
) : FaultData
