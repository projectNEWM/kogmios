package io.newm.kogmios.protocols.model.fault

import com.fasterxml.jackson.annotation.JsonProperty

import com.fasterxml.jackson.annotation.JsonTypeName

import io.newm.kogmios.protocols.model.UtxoOutputValue

/**
 * One of the input provided as collateral carries something else than Ada tokens. Only Ada can be used as collateral. Since the Babbage era, you also have the option to set a 'collateral return' or 'collateral change' output in order to send the surplus non-Ada tokens to it. Regardless, the field 'data.unsuitableCollateralValue' indicates the actual collateral value found by the ledger
 */

@JsonTypeName("3133")
data class NonAdaCollateralFault(
    @param:JsonProperty(value = "code", required = true)
    @get:JsonProperty("code")
    override val code: Long,
    @param:JsonProperty(value = "message", required = true)
    @get:JsonProperty("message")
    override val message: String,
    @param:JsonProperty(value = "data", required = true)
    @get:JsonProperty("data")
    override val data: NonAdaCollateralFaultData,
) : Fault

data class NonAdaCollateralFaultData(
    @param:JsonProperty(value = "unsuitableCollateralValue", required = true)
    @get:JsonProperty("unsuitableCollateralValue")
    val unsuitableCollateralValue: UtxoOutputValue,
) : FaultData
