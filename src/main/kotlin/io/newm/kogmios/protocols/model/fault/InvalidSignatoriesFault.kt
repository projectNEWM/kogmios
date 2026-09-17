package io.newm.kogmios.protocols.model.fault

import com.fasterxml.jackson.annotation.JsonProperty

import com.fasterxml.jackson.annotation.JsonTypeName

/**
 * Some signatures are invalid. Only the serialised transaction *body*, without metadata or witnesses, must be signed.
 */

@JsonTypeName("3100")
data class InvalidSignatoriesFault(
    @param:JsonProperty(value = "code", required = true)
    @get:JsonProperty("code")
    override val code: Long,
    @param:JsonProperty(value = "message", required = true)
    @get:JsonProperty("message")
    override val message: String,
    @param:JsonProperty(value = "data", required = true)
    @get:JsonProperty("data")
    override val data: InvalidSignatoriesFaultData,
) : Fault

data class InvalidSignatoriesFaultData(
    @param:JsonProperty(value = "invalidSignatories", required = true)
    @get:JsonProperty("invalidSignatories")
    val invalidSignatories: List<String>,
) : FaultData
