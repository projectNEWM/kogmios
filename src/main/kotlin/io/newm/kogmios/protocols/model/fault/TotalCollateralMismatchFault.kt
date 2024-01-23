package io.newm.kogmios.protocols.model.fault

import io.newm.kogmios.protocols.model.Ada
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * There's a mismatch between the declared total collateral amount, and the value computed from the inputs and outputs. These must match exactly. The field 'data.declaredTotalCollateral' reports the amount declared in the transaction whereas 'data.computedTotalCollateral' refers to the amount actually computed.
 */
@Serializable
@SerialName("3135")
data class TotalCollateralMismatchFault(
    @SerialName("code")
    override val code: Long,
    @SerialName("message")
    override val message: String,
    @SerialName("data")
    override val data: TotalCollateralMismatchFaultData,
) : Fault

@Serializable
data class TotalCollateralMismatchFaultData(
    @SerialName("declaredTotalCollateral")
    val declaredTotalCollateral: Ada,
    @SerialName("computedTotalCollateral")
    val computedTotalCollateral: Ada,
) : FaultData
