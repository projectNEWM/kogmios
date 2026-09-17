package io.newm.kogmios.protocols.model.fault

import com.fasterxml.jackson.annotation.JsonProperty

import com.fasterxml.jackson.annotation.JsonTypeName

@JsonTypeName("-32602")
data class EvaluateTransactionDeserializationErrorFault(
    @param:JsonProperty(value = "code", required = true)
    @get:JsonProperty("code")
    override val code: Long,
    @param:JsonProperty(value = "message", required = true)
    @get:JsonProperty("message")
    override val message: String,
    @param:JsonProperty(value = "data", required = true)
    @get:JsonProperty("data")
    override val data: EvaluateTransactionDeserializationError,
) : Fault

data class EvaluateTransactionDeserializationError(
    @param:JsonProperty(value = "shelley", required = true)
    @get:JsonProperty("shelley")
    val shelley: String,
    @param:JsonProperty(value = "allegra", required = true)
    @get:JsonProperty("allegra")
    val allegra: String,
    @param:JsonProperty(value = "mary", required = true)
    @get:JsonProperty("mary")
    val mary: String,
    @param:JsonProperty(value = "alonzo", required = true)
    @get:JsonProperty("alonzo")
    val alonzo: String,
    @param:JsonProperty(value = "babbage", required = true)
    @get:JsonProperty("babbage")
    val babbage: String,
    @param:JsonProperty(value = "conway", required = true)
    @get:JsonProperty("conway")
    val conway: String,
) : FaultData
