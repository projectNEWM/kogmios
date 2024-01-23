package io.newm.kogmios.protocols.model.fault

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * This fault is returned when the transaction contains scripts that are not required for the
 * transaction to be valid.
 */
@Serializable
@SerialName("3104")
data class ExtraneousScriptsFault(
    @SerialName("code")
    override val code: Long,
    @SerialName("message")
    override val message: String,
    @SerialName("data")
    override val data: ExtraneousScriptsFaultData,
) : Fault

@Serializable
data class ExtraneousScriptsFaultData(
    @SerialName("extraneousScripts")
    val extraneousScripts: List<String>,
) : FaultData
