package io.newm.kogmios.protocols.model.fault

import com.fasterxml.jackson.annotation.JsonProperty

import com.fasterxml.jackson.annotation.JsonTypeName

/**
 * The transaction failed because the provided script integrity hash doesn't match the computed one. This is crucial for ensuring the integrity of cost models and Plutus version used during script execution. The field 'data.providedScriptIntegrity' correspond to what was given, if any, and 'data.computedScriptIntegrity' is what was expected. If the latter is null, this means you shouldn't have included a script integrity hash to begin with.
 */

@JsonTypeName("3113")
data class ScriptIntegrityHashMismatchFault(
    @param:JsonProperty(value = "code", required = true)
    @get:JsonProperty("code")
    override val code: Long,
    @param:JsonProperty(value = "message", required = true)
    @get:JsonProperty("message")
    override val message: String,
    @param:JsonProperty(value = "data", required = true)
    @get:JsonProperty("data")
    override val data: ScriptIntegrityHashMismatchFaultData,
) : Fault

data class ScriptIntegrityHashMismatchFaultData(
    val providedScriptIntegrity: String? = null,
    val computedScriptIntegrity: String? = null,
) : FaultData
