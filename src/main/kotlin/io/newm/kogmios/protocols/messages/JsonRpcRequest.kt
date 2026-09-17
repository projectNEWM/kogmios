package io.newm.kogmios.protocols.messages

import com.fasterxml.jackson.annotation.JsonIgnore
import io.newm.kogmios.protocols.Const.JSONRPC_VERSION
import kotlinx.coroutines.CompletableDeferred

/**
 * Base class for all Ogmios requests
 */
sealed class JsonRpcRequest {
    val jsonrpc: String = JSONRPC_VERSION

    abstract val method: String

    abstract val id: String

    @get:JsonIgnore
    val completableDeferred: CompletableDeferred<JsonRpcSuccessResponse> = CompletableDeferred()
}
