package io.projectnewm.kogmios.protocols.localstatequery

import io.projectnewm.kogmios.protocols.model.PointOrOrigin
import io.projectnewm.kogmios.protocols.model.Query
import kotlinx.coroutines.CompletableDeferred
import kotlinx.serialization.SerialName


/**
 * Acquire a ledger state for the Local State Query miniprotocol. If you don't specify a ChainPoint argument,
 * the ledger tip will be acquired.
 */
@kotlinx.serialization.Serializable
@SerialName("Query")
data class MsgQuery(
    @SerialName("args")
    val args: Query,
    @kotlinx.serialization.Transient
    val completableDeferred: CompletableDeferred<MsgQueryResponse> = CompletableDeferred(),
) : JsonWspRequest()

// JSON Example
// {
//    "type": "jsonwsp/request",
//    "version": "1.0",
//    "servicename": "ogmios",
//    "methodname": "Query",
//    "args": { "query": "chainTip" }
//}