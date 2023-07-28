package io.newm.kogmios.protocols.model.fault

import io.newm.kogmios.protocols.model.UtxoOutputReference
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 *
 */
@Serializable
@SerialName("3013")
data class UnsuitableOutputReferenceFault(
    @SerialName("code")
    override val code: Long,
    @SerialName("message")
    override val message: String,
    @SerialName("data")
    override val data: UnsuitableOutputReferenceFaultData,
) : Fault

@Serializable
data class UnsuitableOutputReferenceFaultData(
    @SerialName("outputReference")
    val outputReference: UtxoOutputReference,
) : FaultData
