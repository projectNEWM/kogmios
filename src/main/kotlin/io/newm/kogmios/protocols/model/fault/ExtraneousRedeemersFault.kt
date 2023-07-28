package io.newm.kogmios.protocols.model.fault

import io.newm.kogmios.protocols.model.RedeemerPointer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Extraneous (non-required) redeemers found in the transaction. There are some redeemers that aren't pointing to any script. This could be because you've left some orphan redeemer behind, because they are pointing at the wrong thing or because you forgot to include their associated validator. Either way, the field 'data.extraneousRedeemers' lists the different orphan redeemer pointers.
 */
@Serializable
@SerialName("3110")
data class ExtraneousRedeemersFault(
    @SerialName("code")
    override val code: Long,
    @SerialName("message")
    override val message: String,
    @SerialName("data")
    override val data: ExtraneousRedeemersFaultData,
) : Fault

@Serializable
data class ExtraneousRedeemersFaultData(
    @SerialName("extraneousRedeemers")
    val extraneousRedeemers: List<RedeemerPointer>,
) : FaultData
