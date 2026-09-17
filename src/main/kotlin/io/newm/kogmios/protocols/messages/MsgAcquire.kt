package io.newm.kogmios.protocols.messages

import com.fasterxml.jackson.annotation.JsonProperty

import io.newm.kogmios.protocols.model.PointOrOrigin

import java.util.UUID

/**
 * Acquire a ledger state for the Local State Query miniprotocol. If you don't specify a ChainPoint argument,
 * the ledger tip will be acquired.
 */

data class MsgAcquire(
    override val method: String = METHOD_NAME,
    @param:JsonProperty(value = "params", required = true)
    @get:JsonProperty("params")
    val params: PointOrOrigin,
    override val id: String = "$METHOD_NAME: ${UUID.randomUUID()}",
) : JsonRpcRequest() {
    companion object {
        const val METHOD_NAME = "acquireLedgerState"
    }
}
