package io.newm.kogmios.protocols.model.fault

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Invalid transaction submitted as valid, or vice-versa. Since Alonzo, the ledger may allow invalid transactions to be submitted and included on-chain, provided that they leave a collateral value as compensation. This prevent certain class of attacks. As a consequence, transactions now have a validity tag with them. Your transaction did not match what that validity tag is stating. The field 'data.declaredSpending' indicates what the transaction is supposed to consume (collaterals or inputs) and the field 'data.mismatchReason' provides more information about the mismatch.
 */
@Serializable
@SerialName("3136")
data class SpendsMismatchFault(
    @SerialName("code")
    override val code: Long,
    @SerialName("message")
    override val message: String,
    @SerialName("data")
    override val data: SpendsMismatchFaultData,
) : Fault

@Serializable
data class SpendsMismatchFaultData(
    @SerialName("declaredSpending")
    val declaredSpending: String? = null,
    @SerialName("mismatchReason")
    val mismatchReason: String,
) : FaultData
