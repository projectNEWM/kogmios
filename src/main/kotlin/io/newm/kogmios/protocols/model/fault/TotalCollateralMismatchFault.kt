package io.newm.kogmios.protocols.model.fault

import com.fasterxml.jackson.annotation.JsonProperty

import com.fasterxml.jackson.annotation.JsonTypeName

import io.newm.kogmios.protocols.model.Ada

/**
 * There's a mismatch between the declared total collateral amount, and the value computed from the inputs and outputs. These must match exactly. The field 'data.declaredTotalCollateral' reports the amount declared in the transaction whereas 'data.computedTotalCollateral' refers to the amount actually computed.
 */

@JsonTypeName("3135")
data class TotalCollateralMismatchFault(
    @param:JsonProperty(value = "code", required = true)
    @get:JsonProperty("code")
    override val code: Long,
    @param:JsonProperty(value = "message", required = true)
    @get:JsonProperty("message")
    override val message: String,
    @param:JsonProperty(value = "data", required = true)
    @get:JsonProperty("data")
    override val data: TotalCollateralMismatchFaultData,
) : Fault

data class TotalCollateralMismatchFaultData(
    @param:JsonProperty(value = "declaredTotalCollateral", required = true)
    @get:JsonProperty("declaredTotalCollateral")
    val declaredTotalCollateral: Ada,
    @param:JsonProperty(value = "computedTotalCollateral", required = true)
    @get:JsonProperty("computedTotalCollateral")
    val computedTotalCollateral: Ada,
) : FaultData
