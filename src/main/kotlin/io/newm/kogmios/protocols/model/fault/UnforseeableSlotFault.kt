package io.newm.kogmios.protocols.model.fault

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.math.BigInteger

/**
 * One of the transaction validity bound is outside any foreseeable future. The vision of the ledger in the future is limited because the ledger cannot guarantee that the chain will not hard-fork into a version of the protocol working with a different set of parameters (or even, working with the same consensus protocol). However, the protocol cannot fork in less than `k` blocks, where `k` is the security parameter of the chain. Plus, Ouroboros Praos ensures that there are at least `k` blocks produced in a window of 3 * k / f slots, where `f` is the density parameter, also known as the active slot coefficient. Short story short, you can only set validity interval in a short timespan, which is around ~36h in the future on Mainnet at the moment of writing this error message. The field 'data.unforeseeableSlot' indicates the slot which couldn't be converted to a POSIX time due to hard fork uncertainty.
 */
@Serializable
@SerialName("3130")
data class UnforseeableSlotFault(
    @SerialName("code")
    override val code: Long,
    @SerialName("message")
    override val message: String,
    @SerialName("data")
    override val data: UnforseeableSlotFaultData,
) : Fault

@Serializable
data class UnforseeableSlotFaultData(
    @SerialName("unforeseeableSlot")
    @Contextual
    val unforeseeableSlot: BigInteger,
) : FaultData
