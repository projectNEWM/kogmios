package io.newm.kogmios.protocols.model.fault

import com.fasterxml.jackson.annotation.JsonProperty

import com.fasterxml.jackson.annotation.JsonTypeName

/**
 * Happens when attempting to evaluate execution units on a node that isn't enough synchronized.
 */

@JsonTypeName("3003")
data class NodeTipTooOldFault(
    @param:JsonProperty(value = "code", required = true)
    @get:JsonProperty("code")
    override val code: Long,
    @param:JsonProperty(value = "message", required = true)
    @get:JsonProperty("message")
    override val message: String,
    @param:JsonProperty(value = "data", required = true)
    @get:JsonProperty("data")
    override val data: NodeTipTooOldFaultData,
) : Fault

data class NodeTipTooOldFaultData(
    @param:JsonProperty(value = "minimumRequiredEra", required = true)
    @get:JsonProperty("minimumRequiredEra")
    val minimumRequiredEra: String,
    @param:JsonProperty(value = "currentNodeEra", required = true)
    @get:JsonProperty("currentNodeEra")
    val currentNodeEra: String,
) : FaultData
