package io.newm.kogmios.protocols.messages

import java.util.UUID

/**
 * Release the Acquired ledger state for the Local State Query miniprotocol.
 */

data class MsgRelease(
    override val method: String = METHOD_NAME,
    override val id: String = "$method: ${UUID.randomUUID()}",
) : JsonRpcRequest() {
    companion object {
        const val METHOD_NAME = "releaseLedgerState"
    }
}
