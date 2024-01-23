package io.newm.kogmios.protocols.model.fault

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * The era of the transaction does not match the era of the ledger.
 */
@Serializable
@SerialName("3005")
data class EraMismatchFault(
    @SerialName("code")
    override val code: Long,
    @SerialName("message")
    override val message: String,
    @SerialName("data")
    override val data: EraMismatchFaultData,
) : Fault

@Serializable
data class EraMismatchFaultData(
    @SerialName("queryEra")
    val queryEra: String,
    @SerialName("ledgerEra")
    val ledgerEra: String,
) : FaultData
