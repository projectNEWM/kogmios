package io.newm.kogmios.protocols.messages

import java.util.UUID

/**
 * Request the Next transaction from the mempool snapshot
 */

data class MsgNextTransaction(
    override val method: String = METHOD_NAME,
    val params: Fields = Fields(),
    override val id: String = "$method: ${UUID.randomUUID()}",
) : JsonRpcRequest() {
    companion object {
        const val METHOD_NAME = "nextTransaction"
    }
}

data class Fields(
    val fields: String = "all",
)
