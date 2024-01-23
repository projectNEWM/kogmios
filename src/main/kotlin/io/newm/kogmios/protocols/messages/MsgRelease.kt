package io.newm.kogmios.protocols.messages

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.UUID

/**
 * Release the Acquired ledger state for the Local State Query miniprotocol.
 */
@Serializable
data class MsgRelease(
    @SerialName("method")
    override val method: String = METHOD_NAME,
    @SerialName("id")
    override val id: String = "$method: ${UUID.randomUUID()}",
) : JsonRpcRequest() {
    companion object {
        const val METHOD_NAME = "releaseLedgerState"
    }
}
