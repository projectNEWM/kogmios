package io.projectnewm.kogmios.protocols.messages

import io.projectnewm.kogmios.protocols.model.FindIntersect
import kotlinx.coroutines.CompletableDeferred
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Acquire a point of intersection for syncing the blockchain
 */
@Serializable
@SerialName("FindIntersect")
data class MsgFindIntersect(
    @SerialName("args")
    val args: FindIntersect,
    @SerialName("mirror")
    override val mirror: String,
    @kotlinx.serialization.Transient
    val completableDeferred: CompletableDeferred<MsgFindIntersectResponse> = CompletableDeferred(),
) : JsonWspRequest()

// JSON Example
// {
//    "type": "jsonwsp/request",
//    "version": "1.0",
//    "servicename": "ogmios",
//    "methodname": "FindIntersect",
//    "args": {
//      "points": [
//        {
//          "slot": 18446744073709552000,
//          "hash": "c248757d390181c517a5beadc9c3fe64bf821d3e889a963fc717003ec248757d"
//        }
//      ]
//    }
// }
