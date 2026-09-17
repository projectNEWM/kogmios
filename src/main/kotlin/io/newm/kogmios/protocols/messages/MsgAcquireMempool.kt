package io.newm.kogmios.protocols.messages

import java.util.UUID

/**
 * Acquire a mempool snapshot
 */

data class MsgAcquireMempool(
    override val method: String = METHOD_NAME,
    override val id: String = "$method: ${UUID.randomUUID()}",
) : JsonRpcRequest() {
    companion object {
        const val METHOD_NAME = "acquireMempool"
    }
}
