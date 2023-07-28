package io.newm.kogmios.protocols.model.fault

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * A stake pool retirement certificate is trying to retire too late in the future. Indeed, there's a maximum delay for stake pool retirement, controlled by a protocol parameter. The field 'data.currentEpoch' indicates the current epoch known of the ledger, 'data.declaredEpoch' refers to the epoch declared in the retirement certificate and 'data.firstInvalidEpoch' is the first epoch considered invalid (too far) for retirement
 */
@Serializable
@SerialName("3142")
data class RetirementTooLateFault(
    @SerialName("code")
    override val code: Long,
    @SerialName("message")
    override val message: String,
    @SerialName("data")
    override val data: RetirementTooLateFaultData,
) : Fault

@Serializable
data class RetirementTooLateFaultData(
    @SerialName("currentEpoch")
    val currentEpoch: Long,
    @SerialName("declaredEpoch")
    val declaredEpoch: Long,
    @SerialName("firstInvalidEpoch")
    val firstInvalidEpoch: Long,
) : FaultData
