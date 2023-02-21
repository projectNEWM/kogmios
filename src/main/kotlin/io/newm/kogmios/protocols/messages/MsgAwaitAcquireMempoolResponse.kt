package io.newm.kogmios.protocols.messages

import kotlinx.serialization.*
import kotlinx.serialization.json.*
import java.math.BigInteger

/**
 * Response that comes back from Ogmios after an AwaitAcquire message is sent.
 */
@Serializable
@SerialName("AwaitAcquire")
data class MsgAwaitAcquireMempoolResponse(
    @SerialName("result")
    val result: AwaitAcquireMempoolResponse,
    @SerialName("reflection")
    override val reflection: String,
) : JsonWspResponse()

@Serializable
data class AwaitAcquireMempoolResponse(
    @SerialName("AwaitAcquired")
    val awaitAcquired: AwaitAcquired,
)

@Serializable
data class AwaitAcquired(
    @SerialName("slot")
    @Contextual
    val slot: BigInteger,
)
