package io.newm.kogmios.protocols.model.fault

import com.fasterxml.jackson.annotation.JsonProperty

import com.fasterxml.jackson.annotation.JsonTypeName

/**
 * Some of the (V1) scripts failed to evaluate to a positive outcome.
 */

@JsonTypeName("3012")
data class ValidationFailureFault(
    @param:JsonProperty(value = "code", required = true)
    @get:JsonProperty("code")
    override val code: Long,
    @param:JsonProperty(value = "message", required = true)
    @get:JsonProperty("message")
    override val message: String,
    @param:JsonProperty(value = "data", required = true)
    @get:JsonProperty("data")
    override val data: ValidationFailureFaultData,
) : Fault

data class ValidationFailureFaultData(
    @param:JsonProperty(value = "validationError", required = true)
    @get:JsonProperty("validationError")
    val validationError: String,
    @param:JsonProperty(value = "traces", required = true)
    @get:JsonProperty("traces")
    val traces: List<String>,
) : FaultData
