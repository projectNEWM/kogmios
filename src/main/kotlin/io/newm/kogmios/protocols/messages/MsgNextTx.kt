package io.newm.kogmios.protocols.messages

import kotlinx.coroutines.CompletableDeferred
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Request the Next transaction from the mempool snapshot
 */
@Serializable
@SerialName("NextTx")
data class MsgNextTx(
    @SerialName("args")
    val args: Fields = Fields(),
    @SerialName("mirror")
    override val mirror: String,
    @kotlinx.serialization.Transient
    val completableDeferred: CompletableDeferred<MsgNextTxResponse> = CompletableDeferred(),
) : JsonWspRequest()

@Serializable
data class Fields(
    @SerialName("fields")
    val fields: String = "all"
)
// JSON Example
// {
//    "type": "jsonwsp/request",
//    "version": "1.0",
//    "servicename": "ogmios",
//    "methodname": "NextTx",
//    "args": { "fields": "all" }
// }
