package io.newm.kogmios.protocols.model.fault

import com.fasterxml.jackson.annotation.JsonProperty

import com.fasterxml.jackson.annotation.JsonTypeName

/**
 * The transaction references an unknown stake pool as a target for delegation or update. Double-check the pool id mentioned in 'data.unknownStakePool'. Note also that order in which transactions are submitted matters; if you're trying to register a pool and delegate to it in one go, make sure to submit transactions in the right order.
 */

@JsonTypeName("3140")
data class UnknownStakePoolFault(
    @param:JsonProperty(value = "code", required = true)
    @get:JsonProperty("code")
    override val code: Long,
    @param:JsonProperty(value = "message", required = true)
    @get:JsonProperty("message")
    override val message: String,
    @param:JsonProperty(value = "data", required = true)
    @get:JsonProperty("data")
    override val data: UnknownStakePoolFaultData,
) : Fault

data class UnknownStakePoolFaultData(
    @param:JsonProperty(value = "unknownStakePool", required = true)
    @get:JsonProperty("unknownStakePool")
    val unknownStakePool: String,
) : FaultData
