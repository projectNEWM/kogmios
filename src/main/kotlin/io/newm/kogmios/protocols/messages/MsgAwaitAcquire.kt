package io.newm.kogmios.protocols.messages

import io.newm.kogmios.protocols.model.EmptyObject
import kotlinx.coroutines.CompletableDeferred
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Acquire a mempool snapshot
 */
@Serializable
@SerialName("AwaitAcquire")
data class MsgAwaitAcquire(
    @SerialName("args")
    val args: EmptyObject,
    @SerialName("mirror")
    override val mirror: String,
    @kotlinx.serialization.Transient
    val completableDeferred: CompletableDeferred<MsgAwaitAcquireMempoolResponse> = CompletableDeferred(),
) : JsonWspRequest()

// JSON Example
// {
//    "type": "jsonwsp/request",
//    "version": "1.0",
//    "servicename": "ogmios",
//    "methodname": "AwaitAcquire",
//    "args": { }
// }
