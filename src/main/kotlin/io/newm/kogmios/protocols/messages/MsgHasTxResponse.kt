package io.newm.kogmios.protocols.messages

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Response that comes back from Ogmios after a hasTx mempool message is sent.
 */
@Serializable
@SerialName("HasTx")
data class MsgHasTxResponse(
    @SerialName("result")
    val result: Boolean,
    @SerialName("reflection")
    override val reflection: String,
) : JsonWspResponse()

// JSON Example
// {
//    "type": "jsonwsp/response",
//    "version": "1.0",
//    "servicename": "ogmios",
//    "methodname": "HasTx",
//    "result": "true",
//    "reflection": null
// }
