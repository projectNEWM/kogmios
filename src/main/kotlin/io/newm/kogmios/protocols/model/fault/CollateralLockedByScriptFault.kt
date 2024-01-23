package io.newm.kogmios.protocols.model.fault

import io.newm.kogmios.protocols.model.UtxoOutputReference
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Invalid choice of collateral: an input provided for collateral is locked by script. Collateral inputs must be spendable, and the ledger must be able to assert their validity during the first phase of validations (a.k.a phase-1). This discards any input locked by a Plutus script to be used as collateral. Note that for some reason inputs locked by native scripts are also excluded from candidates collateral. The field 'data.unsuitableCollateralInputs' lists all the problematic output references.
 */
@Serializable
@SerialName("3129")
data class CollateralLockedByScriptFault(
    @SerialName("code")
    override val code: Long,
    @SerialName("message")
    override val message: String,
    @SerialName("data")
    override val data: CollateralLockedByScriptFaultData,
) : Fault

@Serializable
data class CollateralLockedByScriptFaultData(
    @SerialName("unsuitableCollateralInputs")
    val unsuitableCollateralInputs: List<UtxoOutputReference>,
) : FaultData
