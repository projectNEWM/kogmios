package io.newm.kogmios.protocols.messages

import com.fasterxml.jackson.annotation.JsonProperty

import com.fasterxml.jackson.annotation.JsonIgnore
import io.newm.kogmios.protocols.model.fault.Fault

/**
 * Container for an error response from Ogmios
 */
data class JsonRpcErrorResponse(
    @param:JsonProperty(value = "error", required = true)
    @get:JsonProperty("error")
    val error: Fault,
    @param:JsonProperty(value = "id", required = true)
    @get:JsonProperty("id")
    override val id: String,
    @get:JsonIgnore
    val cause: Throwable? = null,
) : JsonRpcResponse()
