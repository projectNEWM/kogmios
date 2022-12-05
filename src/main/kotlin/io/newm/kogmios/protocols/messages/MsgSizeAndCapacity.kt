package io.newm.kogmios.protocols.messages

import io.newm.kogmios.protocols.model.EmptyObject
import kotlinx.coroutines.CompletableDeferred
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Check the mempool size and capacity values
 */
@Serializable
@SerialName("SizeAndCapacity")
data class MsgSizeAndCapacity(
    @SerialName("args")
    val args: EmptyObject = EmptyObject(),
    @SerialName("mirror")
    override val mirror: String,
    @kotlinx.serialization.Transient
    val completableDeferred: CompletableDeferred<MsgSizeAndCapacityResponse> = CompletableDeferred(),
) : JsonWspRequest()

// JSON Example
// {
//    "type": "jsonwsp/request",
//    "version": "1.0",
//    "servicename": "ogmios",
//    "methodname": "SizeAndCapacity",
//    "args": {}
// }
