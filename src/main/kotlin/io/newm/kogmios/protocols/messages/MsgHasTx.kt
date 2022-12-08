package io.newm.kogmios.protocols.messages

import kotlinx.coroutines.CompletableDeferred
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Check whether the mempool snapshot has a given transaction in it.
 */
@Serializable
@SerialName("HasTx")
data class MsgHasTx(
    @SerialName("args")
    val args: HasTx,
    @SerialName("mirror")
    override val mirror: String,
    @kotlinx.serialization.Transient
    val completableDeferred: CompletableDeferred<MsgHasTxResponse> = CompletableDeferred(),
) : JsonWspRequest()

@Serializable
data class HasTx(
    @SerialName("id")
    val id: String,
)

// JSON Example
// {
//    "type": "jsonwsp/request",
//    "version": "1.0",
//    "servicename": "ogmios",
//    "methodname": "HasTx",
//    "args": { "id": "7f042e1a54b9f699961de8a47543d4c4cef0bc5bc5e406194d9952667e2c077d" }
// }
