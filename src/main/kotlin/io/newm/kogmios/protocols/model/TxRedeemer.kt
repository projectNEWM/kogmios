package io.newm.kogmios.protocols.model

import com.fasterxml.jackson.annotation.JsonProperty

data class TxRedeemer(
    @param:JsonProperty(value = "executionUnits", required = true)
    @get:JsonProperty("executionUnits")
    val executionUnits: ExecutionUnits,
    @param:JsonProperty(value = "redeemer", required = true)
    @get:JsonProperty("redeemer")
    val redeemer: String,
    @param:JsonProperty(value = "validator", required = true)
    @get:JsonProperty("validator")
    val validator: Validator,
)
