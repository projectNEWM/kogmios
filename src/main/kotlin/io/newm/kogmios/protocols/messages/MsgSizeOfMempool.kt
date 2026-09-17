package io.newm.kogmios.protocols.messages

import java.util.UUID

/**
 * Check the mempool size and capacity values
 */

data class MsgSizeOfMempool(
    override val method: String = METHOD_NAME,
    override val id: String = "$method: ${UUID.randomUUID()}",
) : JsonRpcRequest() {
    companion object {
        const val METHOD_NAME = "sizeOfMempool"
    }
}
