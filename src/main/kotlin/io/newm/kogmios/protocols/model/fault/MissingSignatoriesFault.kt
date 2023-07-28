package io.newm.kogmios.protocols.model.fault

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Some signatures are missing. A signed transaction must carry signatures for all inputs locked by verification keys or a native script.
 * Transaction may also need signatures for each required extra signatories often required by Plutus Scripts.
 */
@Serializable
@SerialName("3101")
data class MissingSignatoriesFault(
    @SerialName("code")
    override val code: Long,
    @SerialName("message")
    override val message: String,
    @SerialName("data")
    override val data: MissingSignatoriesFaultData,
) : Fault

@Serializable
data class MissingSignatoriesFaultData(
    @SerialName("missingSignatories")
    val missingSignatories: List<String>,
) : FaultData
