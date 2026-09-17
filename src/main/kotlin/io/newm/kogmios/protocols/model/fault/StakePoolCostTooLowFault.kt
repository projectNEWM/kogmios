package io.newm.kogmios.protocols.model.fault

import com.fasterxml.jackson.annotation.JsonProperty

import com.fasterxml.jackson.annotation.JsonTypeName

import io.newm.kogmios.protocols.model.Ada

/**
 * Stake pool cost declared in a registration or update certificate are below the allowed minimum. The minimum cost of a stake pool is fixed by a protocol parameter. The 'data.minimumStakePoolCost' field holds the current value of that parameter whereas 'data.declaredStakePoolCost' indicates which amount was declared.
 */

@JsonTypeName("3143")
data class StakePoolCostTooLowFault(
    @param:JsonProperty(value = "code", required = true)
    @get:JsonProperty("code")
    override val code: Long,
    @param:JsonProperty(value = "message", required = true)
    @get:JsonProperty("message")
    override val message: String,
    @param:JsonProperty(value = "data", required = true)
    @get:JsonProperty("data")
    override val data: StakePoolCostTooLowFaultData,
) : Fault

data class StakePoolCostTooLowFaultData(
    @param:JsonProperty(value = "minimumStakePoolCost", required = true)
    @get:JsonProperty("minimumStakePoolCost")
    val minimumStakePoolCost: Ada,
    @param:JsonProperty(value = "declaredStakePoolCost", required = true)
    @get:JsonProperty("declaredStakePoolCost")
    val declaredStakePoolCost: Ada,
) : FaultData
