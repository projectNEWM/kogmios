package io.newm.kogmios.protocols.messages

import java.util.UUID

/**
 * Request the next block when syncing the blockchain.
 */

data class MsgNextBlock(
    override val method: String = METHOD_NEXT_BLOCK,
    override val id: String = "$method: ${UUID.randomUUID()}",
) : JsonRpcRequest() {
    companion object {
        const val METHOD_NEXT_BLOCK = "nextBlock"
    }
}
