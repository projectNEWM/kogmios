package io.newm.kogmios.protocols.model.fault

import io.newm.kogmios.protocols.model.ValidityInterval
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * The transaction is outside of its validity interval. It was either submitted too early or too late. A transaction that has a lower validity bound can only be accepted by the ledger (and make it to the mempool) if the ledger's current slot is greater than the specified bound. The upper bound works similarly, as a time to live. The field 'data.currentSlot' contains the current slot as known of the ledger (this may be different from the current network slot if the ledger is still catching up). The field 'data.validityInterval' is a reminder of the validity interval provided with the transaction.
 */
@Serializable
@SerialName("3118")
data class OutsideOfValidityIntervalFault(
    @SerialName("code")
    override val code: Long,
    @SerialName("message")
    override val message: String,
    @SerialName("data")
    override val data: OutsideOfValidityIntervalFaultData,
) : Fault

@Serializable
data class OutsideOfValidityIntervalFaultData(
    @SerialName("validityInterval")
    val validityInterval: ValidityInterval,
    @SerialName("currentSlot")
    val currentSlot: Long,
) : FaultData
