package io.newm.kogmios.protocols.model.fault

import com.fasterxml.jackson.annotation.JsonProperty

import com.fasterxml.jackson.annotation.JsonTypeName

@JsonTypeName("3103")
data class FailingNativeScriptsFault(
    @param:JsonProperty(value = "code", required = true)
    @get:JsonProperty("code")
    override val code: Long,
    @param:JsonProperty(value = "message", required = true)
    @get:JsonProperty("message")
    override val message: String,
    @param:JsonProperty(value = "data", required = true)
    @get:JsonProperty("data")
    override val data: FailingNativeScriptsFaultData,
) : Fault

data class FailingNativeScriptsFaultData(
    @param:JsonProperty(value = "failingNativeScripts", required = true)
    @get:JsonProperty("failingNativeScripts")
    val failingNativeScripts: List<String>,
) : FaultData
