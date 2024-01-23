package io.newm.kogmios.protocols.messages

import io.newm.kogmios.protocols.Const.JSONRPC_VERSION
import kotlinx.coroutines.CompletableDeferred
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

/**
 * Base class for all Ogmios requests
 */
@Serializable
sealed class JsonRpcRequest {
    @SerialName("jsonrpc")
    val jsonrpc: String = JSONRPC_VERSION

    @SerialName("method")
    abstract val method: String

    @SerialName("id")
    abstract val id: String

    @Transient
    val completableDeferred: CompletableDeferred<JsonRpcSuccessResponse> = CompletableDeferred()
}
