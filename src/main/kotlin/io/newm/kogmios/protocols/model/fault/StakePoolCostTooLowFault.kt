package io.newm.kogmios.protocols.model.fault

import io.newm.kogmios.protocols.model.Ada
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Stake pool cost declared in a registration or update certificate are below the allowed minimum. The minimum cost of a stake pool is fixed by a protocol parameter. The 'data.minimumStakePoolCost' field holds the current value of that parameter whereas 'data.declaredStakePoolCost' indicates which amount was declared.
 */
@Serializable
@SerialName("3143")
data class StakePoolCostTooLowFault(
    @SerialName("code")
    override val code: Long,
    @SerialName("message")
    override val message: String,
    @SerialName("data")
    override val data: StakePoolCostTooLowFaultData,
) : Fault

@Serializable
data class StakePoolCostTooLowFaultData(
    @SerialName("minimumStakePoolCost")
    val minimumStakePoolCost: Ada,
    @SerialName("declaredStakePoolCost")
    val declaredStakePoolCost: Ada,
) : FaultData
