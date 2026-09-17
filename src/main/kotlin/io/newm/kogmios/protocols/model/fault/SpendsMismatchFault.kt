package io.newm.kogmios.protocols.model.fault

import com.fasterxml.jackson.annotation.JsonProperty

import com.fasterxml.jackson.annotation.JsonTypeName

/**
 * Invalid transaction submitted as valid, or vice-versa. Since Alonzo, the ledger may allow invalid transactions to be submitted and included on-chain, provided that they leave a collateral value as compensation. This prevent certain class of attacks. As a consequence, transactions now have a validity tag with them. Your transaction did not match what that validity tag is stating. The field 'data.declaredSpending' indicates what the transaction is supposed to consume (collaterals or inputs) and the field 'data.mismatchReason' provides more information about the mismatch.
 @param:JsonProperty(value = "code", required = true)
 @get:JsonProperty("code")
 */

@JsonTypeName("3136")
data class SpendsMismatchFault(
    override val code: Long,
    @param:JsonProperty(value = "message", required = true)
    @get:JsonProperty("message")
    override val message: String,
    @param:JsonProperty(value = "data", required = true)
    @get:JsonProperty("data")
    override val data: SpendsMismatchFaultData,
) : Fault

data class SpendsMismatchFaultData(
    val declaredSpending: String? = null,
    @param:JsonProperty(value = "mismatchReason", required = true)
    @get:JsonProperty("mismatchReason")
    val mismatchReason: String,
) : FaultData
