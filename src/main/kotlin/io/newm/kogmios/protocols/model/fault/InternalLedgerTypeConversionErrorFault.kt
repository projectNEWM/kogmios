package io.newm.kogmios.protocols.model.fault

import com.fasterxml.jackson.annotation.JsonProperty

import com.fasterxml.jackson.annotation.JsonTypeName

/**
 * Whoopsie, the ledger failed to upgrade an data-type from an earlier era into data of a newer era. If you ever run into this, please report the issue as you've likely discoverd a critical bug...
 */

@JsonTypeName("3999")
data class InternalLedgerTypeConversionErrorFault(
    @param:JsonProperty(value = "code", required = true)
    @get:JsonProperty("code")
    override val code: Long,
    @param:JsonProperty(value = "message", required = true)
    @get:JsonProperty("message")
    override val message: String,
    override val data: FaultData? = null,
) : Fault
