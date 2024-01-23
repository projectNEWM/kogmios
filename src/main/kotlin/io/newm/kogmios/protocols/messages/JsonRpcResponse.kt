package io.newm.kogmios.protocols.messages

import io.newm.kogmios.protocols.Const.JSONRPC_VERSION
import io.newm.kogmios.serializers.JsonRpcResponseSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Base class for all Ogmios responses.
 */
@Serializable(with = JsonRpcResponseSerializer::class)
sealed class JsonRpcResponse {
    @SerialName("jsonrpc")
    val jsonrpc: String = JSONRPC_VERSION

    @SerialName("id")
    abstract val id: String
}
