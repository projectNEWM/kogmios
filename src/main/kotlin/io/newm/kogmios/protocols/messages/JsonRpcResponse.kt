package io.newm.kogmios.protocols.messages

import io.newm.kogmios.protocols.Const.JSONRPC_VERSION

/**
 * Base class for all Ogmios responses.
 */
sealed class JsonRpcResponse {
    val jsonrpc: String = JSONRPC_VERSION

    abstract val id: String
}
