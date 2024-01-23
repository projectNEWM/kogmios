package io.newm.kogmios.protocols.model.fault

import io.newm.kogmios.protocols.model.Ada
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Insufficient collateral value for Plutus scripts in the transaction. Indeed, when executing scripts, you must provide a collateral amount which minimum is a percentage of the total execution budget for the transaction. The exact percentage is given by a protocol parameter. The field 'data.providedCollateral' indicates the amount currently provided as collateral in the transaction, whereas 'data.minimumRequiredCollateral' indicates the minimum amount expected by the ledger
 */
@Serializable
@SerialName("3128")
data class InsufficientCollateralFault(
    @SerialName("code")
    override val code: Long,
    @SerialName("message")
    override val message: String,
    @SerialName("data")
    override val data: InsufficientCollateralFaultData,
) : Fault

@Serializable
data class InsufficientCollateralFaultData(
    @SerialName("providedCollateral")
    val providedCollateral: Ada,
    @SerialName("minimumRequiredCollateral")
    val minimumRequiredCollateral: Ada,
) : FaultData
