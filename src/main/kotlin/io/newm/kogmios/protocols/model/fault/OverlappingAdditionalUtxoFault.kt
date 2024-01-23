package io.newm.kogmios.protocols.model.fault

import io.newm.kogmios.protocols.model.UtxoOutputReference
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Happens when providing an additional UTXO set which overlaps with the UTXO on-chain.
 */
@Serializable
@SerialName("3002")
data class OverlappingAdditionalUtxoFault(
    @SerialName("code")
    override val code: Long,
    @SerialName("message")
    override val message: String,
    @SerialName("data")
    override val data: OverlappingAdditionalUtxoFaultData,
) : Fault

@Serializable
data class OverlappingAdditionalUtxoFaultData(
    @SerialName("overlappingOutputReferences")
    val overlappingOutputReferences: List<UtxoOutputReference>,
) : FaultData
