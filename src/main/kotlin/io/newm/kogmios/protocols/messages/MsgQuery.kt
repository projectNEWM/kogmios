package io.newm.kogmios.protocols.messages

import io.newm.kogmios.protocols.model.Query
import kotlinx.coroutines.CompletableDeferred
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Message sent to query various ledger state items.
 */
@Serializable
@SerialName("Query")
data class MsgQuery(
    @SerialName("args")
    val args: Query,
    @SerialName("mirror")
    override val mirror: String,
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
// }
