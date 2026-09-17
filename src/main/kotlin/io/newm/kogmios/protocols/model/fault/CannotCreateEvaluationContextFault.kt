package io.newm.kogmios.protocols.model.fault

import com.fasterxml.jackson.annotation.JsonProperty

import com.fasterxml.jackson.annotation.JsonTypeName

/**
 * The transaction is malformed or missing information; making evaluation impossible.
 */

@JsonTypeName("3004")
data class CannotCreateEvaluationContextFault(
    @param:JsonProperty(value = "code", required = true)
    @get:JsonProperty("code")
    override val code: Long,
    @param:JsonProperty(value = "message", required = true)
    @get:JsonProperty("message")
    override val message: String,
    @param:JsonProperty(value = "data", required = true)
    @get:JsonProperty("data")
    override val data: CannotCreateEvaluationContextFaultData,
) : Fault

data class CannotCreateEvaluationContextFaultData(
    @param:JsonProperty(value = "reason", required = true)
    @get:JsonProperty("reason")
    val reason: String,
) : FaultData
