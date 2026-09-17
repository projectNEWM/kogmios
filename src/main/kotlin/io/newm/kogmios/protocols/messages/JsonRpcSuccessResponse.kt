package io.newm.kogmios.protocols.messages

import com.fasterxml.jackson.annotation.JsonTypeInfo
import io.newm.kogmios.protocols.model.result.OgmiosResult

/**
 * Container for a success response from Ogmios
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "method")
sealed class JsonRpcSuccessResponse : JsonRpcResponse() {
    abstract val result: OgmiosResult
}
