package io.newm.kogmios.protocols.messages

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Response that comes back from Ogmios after a release message is sent.
 */
@Serializable
@SerialName("Release")
data class MsgReleaseResponse(
    @SerialName("result")
    val result: String,
    @SerialName("reflection")
    override val reflection: String,
) : JsonWspResponse()

// JSON Example
// {
//    "type": "jsonwsp/response",
//    "version": "1.0",
//    "servicename": "ogmios",
//    "methodname": "Release",
//    "result": "Released",
//    "reflection": null
// }
