package io.newm.kogmios.protocols.messages

import io.newm.kogmios.protocols.model.EmptyObject
import kotlinx.coroutines.CompletableDeferred
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Release mempool snapshot
 */
@Serializable
@SerialName("ReleaseMempool")
data class MsgReleaseMempool(
    @SerialName("args")
    val args: EmptyObject,
    @SerialName("mirror")
    override val mirror: String,
    @kotlinx.serialization.Transient
    val completableDeferred: CompletableDeferred<MsgReleaseMempoolResponse> = CompletableDeferred(),
) : JsonWspRequest()

// JSON Example
// {
//    "type": "jsonwsp/request",
//    "version": "1.0",
//    "servicename": "ogmios",
//    "methodname": "ReleaseMempool",
//    "args": {}
// }
