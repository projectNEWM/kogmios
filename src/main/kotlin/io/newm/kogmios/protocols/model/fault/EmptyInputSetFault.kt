package io.newm.kogmios.protocols.model.fault

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Transaction must have at least one input, but this one has an empty input set. One input is necessary to prevent replayability of transactions, as it piggybacks on the unique spendable property of UTxO.
 */
@Serializable
@SerialName("3121")
data class EmptyInputSetFault(
    @SerialName("code")
    override val code: Long,
    @SerialName("message")
    override val message: String,
    @SerialName("data")
    override val data: FaultData? = null,
) : Fault
