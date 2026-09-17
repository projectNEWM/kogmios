package io.newm.kogmios.protocols.model.fault

import com.fasterxml.jackson.annotation.JsonProperty

import com.fasterxml.jackson.annotation.JsonTypeName

/**
 * Returned when trying to evaluate execution units of an era that is now considered too old and is no longer supported. This can solved by using a more recent transaction format.
 */

@JsonTypeName("3001")
data class UnsupportedEraFault(
    @param:JsonProperty(value = "code", required = true)
    @get:JsonProperty("code")
    override val code: Long,
    @param:JsonProperty(value = "message", required = true)
    @get:JsonProperty("message")
    override val message: String,
    @param:JsonProperty(value = "data", required = true)
    @get:JsonProperty("data")
    override val data: UnsupportedEraFaultData,
) : Fault

data class UnsupportedEraFaultData(
    @param:JsonProperty(value = "unsupportedEra", required = true)
    @get:JsonProperty("unsupportedEra")
    val unsupportedEra: String,
) : FaultData
