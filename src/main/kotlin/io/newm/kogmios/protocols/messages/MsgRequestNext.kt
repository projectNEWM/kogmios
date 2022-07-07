package io.projectnewm.kogmios.protocols.messages

import kotlinx.coroutines.CompletableDeferred
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Acquire a point of intersection for syncing the blockchain
 */
@Serializable
@SerialName("RequestNext")
data class MsgRequestNext(
    @SerialName("mirror")
    override val mirror: String,
    @kotlinx.serialization.Transient
    val completableDeferred: CompletableDeferred<MsgRequestNextResponse> = CompletableDeferred(),
) : JsonWspRequest()

// JSON Example
// {
//    "type": "jsonwsp/request",
//    "version": "1.0",
//    "servicename": "ogmios",
//    "methodname": "RequestNext",
//    "args": {}
// }
