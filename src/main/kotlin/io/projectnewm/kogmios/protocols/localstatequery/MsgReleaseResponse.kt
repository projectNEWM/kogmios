package io.projectnewm.kogmios.protocols.localstatequery

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Response that comes back from Ogmios after a release message is sent.
 */
@Serializable
@SerialName("Release")
data class MsgReleaseResponse(
    @SerialName("result")
    val result: String
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
