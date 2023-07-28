package io.newm.kogmios.exception

import io.newm.kogmios.protocols.messages.JsonRpcErrorResponse
import java.io.IOException

/**
 * Exception thrown when an error occurs while communicating with the Ogmios server.
 */
class KogmiosException(message: String, cause: Throwable, val jsonRpcErrorResponse: JsonRpcErrorResponse) : IOException(message, cause)
