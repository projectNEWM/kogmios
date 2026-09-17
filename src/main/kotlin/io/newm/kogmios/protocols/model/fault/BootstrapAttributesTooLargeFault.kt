package io.newm.kogmios.protocols.model.fault

import com.fasterxml.jackson.annotation.JsonProperty

import com.fasterxml.jackson.annotation.JsonTypeName

import io.newm.kogmios.protocols.model.UtxoOutput

/**
 * Some output associated with legacy / bootstrap (a.k.a. Byron) addresses have attributes that are too large. The field 'data.bootstrapOutputs' lists all affected outputs.
 */

@JsonTypeName("3126")
data class BootstrapAttributesTooLargeFault(
    @param:JsonProperty(value = "code", required = true)
    @get:JsonProperty("code")
    override val code: Long,
    @param:JsonProperty(value = "message", required = true)
    @get:JsonProperty("message")
    override val message: String,
    @param:JsonProperty(value = "data", required = true)
    @get:JsonProperty("data")
    override val data: BootstrapAttributesTooLargeFaultData,
) : Fault

data class BootstrapAttributesTooLargeFaultData(
    @param:JsonProperty(value = "bootstrapOutputs", required = true)
    @get:JsonProperty("bootstrapOutputs")
    val bootstrapOutputs: List<UtxoOutput>,
) : FaultData
