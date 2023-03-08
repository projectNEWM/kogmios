package io.newm.kogmios.protocols.messages

import io.newm.kogmios.protocols.model.PointDetailOrOrigin
import io.newm.kogmios.protocols.model.Tip
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
@SerialName("FindIntersect")
data class MsgFindIntersectResponse(
    @SerialName("result")
    val result: FindIntersectResult,
    @SerialName("reflection")
    override val reflection: String,
) : JsonWspResponse()

@Serializable(with = IntersectionFoundOrIntersectionNotFoundSerializer::class)
abstract class FindIntersectResult

@Serializable
data class IntersectionFound(
    @SerialName("IntersectionFound")
    val intersectionFound: IntersectionFoundData
) : FindIntersectResult()

@Serializable
data class IntersectionFoundData(
    @SerialName("point")
    val point: PointDetailOrOrigin,
    @SerialName("tip")
    val tip: Tip,
)

@Serializable
data class IntersectionNotFound(
    @SerialName("IntersectionNotFound")
    val intersectionNotFound: IntersectionNotFoundData
) : FindIntersectResult()

@Serializable
data class IntersectionNotFoundData(
    @SerialName("tip")
    val tip: Tip,
)

object IntersectionFoundOrIntersectionNotFoundSerializer :
    JsonContentPolymorphicSerializer<FindIntersectResult>(FindIntersectResult::class) {
    override fun selectDeserializer(element: JsonElement): DeserializationStrategy<FindIntersectResult> {
        return if ("IntersectionFound" in element.jsonObject) {
            IntersectionFound.serializer()
        } else {
            IntersectionNotFound.serializer()
        }
    }
}

// JSON Example
// {
//    "type": "jsonwsp/response",
//    "version": "1.0",
//    "servicename": "ogmios",
//    "methodname": "FindIntersect",
//    "result":
//    {
//        "IntersectionFound":
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
//    "reflection": "MsgFindIntersect:a389801e-c76d-4912-8cde-b6ca73a01963"
// }
