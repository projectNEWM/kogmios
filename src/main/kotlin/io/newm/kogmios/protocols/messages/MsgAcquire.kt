package io.newm.kogmios.protocols.messages

import io.newm.kogmios.protocols.model.PointOrOrigin
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.UUID

/**
 * Acquire a ledger state for the Local State Query miniprotocol. If you don't specify a ChainPoint argument,
 * the ledger tip will be acquired.
 */
@Serializable
data class MsgAcquire(
    @SerialName("method")
    override val method: String = METHOD_NAME,
    @SerialName("params")
    val params: PointOrOrigin,
    @SerialName("id")
    override val id: String = "$METHOD_NAME: ${UUID.randomUUID()}",
) : JsonRpcRequest() {
    companion object {
        const val METHOD_NAME = "acquireLedgerState"
    }
}
