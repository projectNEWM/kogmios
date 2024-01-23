package io.newm.kogmios.protocols.model.fault

import io.newm.kogmios.protocols.model.UtxoOutputValue
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * One of the input provided as collateral carries something else than Ada tokens. Only Ada can be used as collateral. Since the Babbage era, you also have the option to set a 'collateral return' or 'collateral change' output in order to send the surplus non-Ada tokens to it. Regardless, the field 'data.unsuitableCollateralValue' indicates the actual collateral value found by the ledger
 */
@Serializable
@SerialName("3133")
data class NonAdaCollateralFault(
    @SerialName("code")
    override val code: Long,
    @SerialName("message")
    override val message: String,
    @SerialName("data")
    override val data: NonAdaCollateralFaultData,
) : Fault

@Serializable
data class NonAdaCollateralFaultData(
    @SerialName("unsuitableCollateralValue")
    val unsuitableCollateralValue: UtxoOutputValue,
) : FaultData
