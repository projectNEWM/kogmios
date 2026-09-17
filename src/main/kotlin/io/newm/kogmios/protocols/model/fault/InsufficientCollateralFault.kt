package io.newm.kogmios.protocols.model.fault

import com.fasterxml.jackson.annotation.JsonProperty

import com.fasterxml.jackson.annotation.JsonTypeName

import io.newm.kogmios.protocols.model.Ada

/**
 * Insufficient collateral value for Plutus scripts in the transaction. Indeed, when executing scripts, you must provide a collateral amount which minimum is a percentage of the total execution budget for the transaction. The exact percentage is given by a protocol parameter. The field 'data.providedCollateral' indicates the amount currently provided as collateral in the transaction, whereas 'data.minimumRequiredCollateral' indicates the minimum amount expected by the ledger
 */

@JsonTypeName("3128")
data class InsufficientCollateralFault(
    @param:JsonProperty(value = "code", required = true)
    @get:JsonProperty("code")
    override val code: Long,
    @param:JsonProperty(value = "message", required = true)
    @get:JsonProperty("message")
    override val message: String,
    @param:JsonProperty(value = "data", required = true)
    @get:JsonProperty("data")
    override val data: InsufficientCollateralFaultData,
) : Fault

data class InsufficientCollateralFaultData(
    @param:JsonProperty(value = "providedCollateral", required = true)
    @get:JsonProperty("providedCollateral")
    val providedCollateral: Ada,
    @param:JsonProperty(value = "minimumRequiredCollateral", required = true)
    @get:JsonProperty("minimumRequiredCollateral")
    val minimumRequiredCollateral: Ada,
) : FaultData
