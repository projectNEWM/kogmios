package io.newm.kogmios.protocols.messages

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Response that comes back from Ogmios after a sizeAndCapacity mempool message is sent.
 */
@Serializable
@SerialName("SizeAndCapacity")
data class MsgSizeAndCapacityResponse(
    @SerialName("result")
    val result: MempoolSizeAndCapacity,
    @SerialName("reflection")
    override val reflection: String,
) : JsonWspResponse()

@Serializable
data class MempoolSizeAndCapacity(
    @SerialName("capacity")
    val capacity: Long,
    @SerialName("currentSize")
    val currentSize: Long,
    @SerialName("numberOfTxs")
    val numberOfTxs: Long,
)

// JSON Example
// {
//    "type": "jsonwsp/response",
//    "version": "1.0",
//    "servicename": "ogmios",
//    "methodname": "SizeAndCapacity",
//    "result": { "capacity": 512000, "currentSize": 0, "numberOfTxs": 0 },
//    "reflection": null
// }
