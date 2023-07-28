package io.newm.kogmios.protocols.model.fault

import io.newm.kogmios.protocols.model.Ada
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Insufficient fee! The transaction doesn't not contain enough fee to cover the minimum required by the protocol. Note that fee depends on (a) a flat cost fixed by the protocol, (b) the size of the serialized transaction, (c) the budget allocated for Plutus script execution. The field 'data.minimumRequiredFee' indicates the minimum required fee whereas 'data.providedFee' refers to the fee currently supplied with the transaction.
 */
@Serializable
@SerialName("3122")
data class TransactionFeeTooSmallFault(
    @SerialName("code")
    override val code: Long,
    @SerialName("message")
    override val message: String,
    @SerialName("data")
    override val data: TransactionFeeTooSmallFaultData,
) : Fault

@Serializable
data class TransactionFeeTooSmallFaultData(
    @SerialName("minimumRequiredFee")
    val minimumRequiredFee: Ada,
    @SerialName("providedFee")
    val providedFee: Ada,
) : FaultData
