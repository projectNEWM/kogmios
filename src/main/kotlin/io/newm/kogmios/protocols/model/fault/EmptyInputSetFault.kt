package io.newm.kogmios.protocols.model.fault

import com.fasterxml.jackson.annotation.JsonProperty

import com.fasterxml.jackson.annotation.JsonTypeName

/**
 * Transaction must have at least one input, but this one has an empty input set. One input is necessary to prevent replayability of transactions, as it piggybacks on the unique spendable property of UTxO.
 */

@JsonTypeName("3121")
data class EmptyInputSetFault(
    @param:JsonProperty(value = "code", required = true)
    @get:JsonProperty("code")
    override val code: Long,
    @param:JsonProperty(value = "message", required = true)
    @get:JsonProperty("message")
    override val message: String,
    override val data: FaultData? = null,
) : Fault
