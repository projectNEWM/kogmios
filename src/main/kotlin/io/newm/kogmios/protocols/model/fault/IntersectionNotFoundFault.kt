package io.newm.kogmios.protocols.model.fault

import com.fasterxml.jackson.annotation.JsonProperty

import com.fasterxml.jackson.annotation.JsonTypeName

import io.newm.kogmios.protocols.model.Tip

/**
 * No intersection found with the requested points.
 */

@JsonTypeName("1000")
data class IntersectionNotFoundFault(
    @param:JsonProperty(value = "code", required = true)
    @get:JsonProperty("code")
    override val code: Long,
    @param:JsonProperty(value = "message", required = true)
    @get:JsonProperty("message")
    override val message: String,
    @param:JsonProperty(value = "data", required = true)
    @get:JsonProperty("data")
    override val data: IntersectionNotFoundFaultData,
) : Fault

data class IntersectionNotFoundFaultData(
    @param:JsonProperty(value = "tip", required = true)
    @get:JsonProperty("tip")
    val tip: Tip,
) : FaultData
