package io.newm.kogmios.protocols.model.fault

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * The transaction failed because the provided script integrity hash doesn't match the computed one. This is crucial for ensuring the integrity of cost models and Plutus version used during script execution. The field 'data.providedScriptIntegrity' correspond to what was given, if any, and 'data.computedScriptIntegrity' is what was expected. If the latter is null, this means you shouldn't have included a script integrity hash to begin with.
 */
@Serializable
@SerialName("3113")
data class ScriptIntegrityHashMismatchFault(
    @SerialName("code")
    override val code: Long,
    @SerialName("message")
    override val message: String,
    @SerialName("data")
    override val data: ScriptIntegrityHashMismatchFaultData,
) : Fault

@Serializable
data class ScriptIntegrityHashMismatchFaultData(
    @SerialName("providedScriptIntegrity")
    val providedScriptIntegrity: String? = null,
    @SerialName("computedScriptIntegrity")
    val computedScriptIntegrity: String? = null,
) : FaultData
