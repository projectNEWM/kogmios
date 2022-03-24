package io.projectnewm.kogmios.protocols.localstatequery

import kotlinx.coroutines.CompletableDeferred
import kotlinx.serialization.SerialName


/**
 * Release the Acquired ledger state for the Local State Query miniprotocol.
 */
@kotlinx.serialization.Serializable
@SerialName("Release")
data class MsgRelease(
    @SerialName("mirror")
    val mirror: String,
    @kotlinx.serialization.Transient
    val completableDeferred: CompletableDeferred<MsgReleaseResponse> = CompletableDeferred(),
) : JsonWspRequest()

// JSON Example
// {
//    "type": "jsonwsp/request",
//    "version": "1.0",
//    "servicename": "ogmios",
//    "methodname": "Release"
// }
