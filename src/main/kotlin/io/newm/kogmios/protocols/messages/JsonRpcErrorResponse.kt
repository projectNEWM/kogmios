package io.newm.kogmios.protocols.messages

import io.newm.kogmios.protocols.model.fault.Fault
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

/**
 * Container for an error response from Ogmios
 */
@Serializable
data class JsonRpcErrorResponse(
    @SerialName("error")
    val error: Fault,
    @SerialName("id")
    override val id: String,
    @Transient
    val cause: Throwable? = null,
) : JsonRpcResponse()
