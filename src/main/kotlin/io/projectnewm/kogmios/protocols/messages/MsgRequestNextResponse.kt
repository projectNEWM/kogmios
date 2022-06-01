package io.projectnewm.kogmios.protocols.messages

import io.projectnewm.kogmios.protocols.model.Block
import io.projectnewm.kogmios.protocols.model.PointDetailOrOrigin
import io.projectnewm.kogmios.protocols.model.Tip
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonObject

/**
 * Response that comes back from Ogmios after a FindIntersect message is sent.
 */
@Serializable
@SerialName("RequestNext")
data class MsgRequestNextResponse(
    @SerialName("result")
    val result: RequestNextResult,
    @SerialName("reflection")
    override val reflection: String,
) : JsonWspResponse()

@Serializable(with = RequestNextResultSerializer::class)
abstract class RequestNextResult

@Serializable
data class RollBackward(
    @SerialName("RollBackward")
    val rollBackward: RollBackwardData
) : RequestNextResult()

@Serializable
data class RollBackwardData(
    @SerialName("point")
    val point: PointDetailOrOrigin,
    @SerialName("tip")
    val tip: Tip,
)

@Serializable
data class RollForward(
    @SerialName("RollForward")
    val rollForward: RollForwardData
) : RequestNextResult()

@Serializable
data class RollForwardData(
    @SerialName("block")
    val block: Block,
    @SerialName("tip")
    val tip: Tip,
)

object RequestNextResultSerializer :
    JsonContentPolymorphicSerializer<RequestNextResult>(RequestNextResult::class) {
    override fun selectDeserializer(element: JsonElement): DeserializationStrategy<out RequestNextResult> {
        return if ("RollForward" in element.jsonObject) {
            RollForward.serializer()
        } else {
            RollBackward.serializer()
        }
    }
}

// JSON Example
// {
//    "type": "jsonwsp/response",
//    "version": "1.0",
//    "servicename": "ogmios",
//    "methodname": "RequestNext",
//    "result":
//    {
//        "RollBackward":
//        {
//            "point":
//            {
//                "slot": 1598399,
//                "hash": "7e16781b40ebf8b6da18f7b5e8ade855d6738095ef2f1c58c77e88b6e45997a4"
//            },
//            "tip":
//            {
//                "slot": 59034755,
//                "hash": "8de250e3cfdbb992a072e6a8deb79676adce9c95a1cfc94426142dd096328584",
//                "blockNo": 3577180
//            }
//        }
//    },
//    "reflection": "MsgRequestNext:a389801e-c76d-4912-8cde-b6ca73a01963"
// }
