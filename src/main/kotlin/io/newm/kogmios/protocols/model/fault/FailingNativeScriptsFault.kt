package io.newm.kogmios.protocols.model.fault

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("3103")
data class FailingNativeScriptsFault(
    @SerialName("code")
    override val code: Long,
    @SerialName("message")
    override val message: String,
    @SerialName("data")
    override val data: FailingNativeScriptsFaultData,
) : Fault

@Serializable
data class FailingNativeScriptsFaultData(
    @SerialName("failingNativeScripts")
    val failingNativeScripts: List<String>,
) : FaultData
