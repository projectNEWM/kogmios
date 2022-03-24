package io.projectnewm.kogmios.protocols.localstatequery

import io.projectnewm.kogmios.protocols.model.PointOrOrigin
import kotlinx.coroutines.CompletableDeferred
import kotlinx.serialization.SerialName


/**
 * Acquire a ledger state for the Local State Query miniprotocol. If you don't specify a ChainPoint argument,
 * the ledger tip will be acquired.
 */
@kotlinx.serialization.Serializable
@SerialName("Release")
data class MsgRelease(
    @SerialName("mirror")
    val mirror: String,
    @kotlinx.serialization.Transient
    val completableDeferred: CompletableDeferred<MsgReleaseResponse> = CompletableDeferred(),
) : JsonWspRequest()

// JSON Example
// {
//    "type": "jsonwsp/request",
//    "version": "1.0",
//    "servicename": "ogmios",
//    "methodname": "Release"
// }
