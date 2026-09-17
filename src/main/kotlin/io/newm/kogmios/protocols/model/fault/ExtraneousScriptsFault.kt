package io.newm.kogmios.protocols.model.fault

import com.fasterxml.jackson.annotation.JsonProperty

import com.fasterxml.jackson.annotation.JsonTypeName

/**
 * This fault is returned when the transaction contains scripts that are not required for the
 * transaction to be valid.
 */

@JsonTypeName("3104")
data class ExtraneousScriptsFault(
    @param:JsonProperty(value = "code", required = true)
    @get:JsonProperty("code")
    override val code: Long,
    @param:JsonProperty(value = "message", required = true)
    @get:JsonProperty("message")
    override val message: String,
    @param:JsonProperty(value = "data", required = true)
    @get:JsonProperty("data")
    override val data: ExtraneousScriptsFaultData,
) : Fault

data class ExtraneousScriptsFaultData(
    @param:JsonProperty(value = "extraneousScripts", required = true)
    @get:JsonProperty("extraneousScripts")
    val extraneousScripts: List<String>,
) : FaultData
