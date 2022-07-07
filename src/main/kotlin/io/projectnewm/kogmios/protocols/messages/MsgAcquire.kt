package io.projectnewm.kogmios.protocols.messages

import io.projectnewm.kogmios.protocols.model.PointOrOrigin
import kotlinx.coroutines.CompletableDeferred
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Acquire a ledger state for the Local State Query miniprotocol. If you don't specify a ChainPoint argument,
 * the ledger tip will be acquired.
 */
@Serializable
@SerialName("Acquire")
data class MsgAcquire(
    @SerialName("args")
    val args: PointOrOrigin,
    @SerialName("mirror")
    override val mirror: String,
    @kotlinx.serialization.Transient
    val completableDeferred: CompletableDeferred<MsgAcquireResponse> = CompletableDeferred(),
) : JsonWspRequest()

// JSON Example
// {
//    "type": "jsonwsp/request",
//    "version": "1.0",
//    "servicename": "ogmios",
//    "methodname": "Acquire",
//    "args": { "point": {slot:1234, hash:"9e871633f7aa356ef11cdcabb6fdd6d8f4b00bc919c57aed71a91af8f86df590" }
// }
