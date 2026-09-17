package io.newm.kogmios.protocols.model.fault

import com.fasterxml.jackson.annotation.JsonProperty

import com.fasterxml.jackson.annotation.JsonTypeName

import io.newm.kogmios.protocols.model.UtxoOutputReference

/**
 * Invalid choice of collateral: an input provided for collateral is locked by script. Collateral inputs must be spendable, and the ledger must be able to assert their validity during the first phase of validations (a.k.a phase-1). This discards any input locked by a Plutus script to be used as collateral. Note that for some reason inputs locked by native scripts are also excluded from candidates collateral. The field 'data.unsuitableCollateralInputs' lists all the problematic output references.
 */

@JsonTypeName("3129")
data class CollateralLockedByScriptFault(
    @param:JsonProperty(value = "code", required = true)
    @get:JsonProperty("code")
    override val code: Long,
    @param:JsonProperty(value = "message", required = true)
    @get:JsonProperty("message")
    override val message: String,
    @param:JsonProperty(value = "data", required = true)
    @get:JsonProperty("data")
    override val data: CollateralLockedByScriptFaultData,
) : Fault

data class CollateralLockedByScriptFaultData(
    @param:JsonProperty(value = "unsuitableCollateralInputs", required = true)
    @get:JsonProperty("unsuitableCollateralInputs")
    val unsuitableCollateralInputs: List<UtxoOutputReference>,
) : FaultData
