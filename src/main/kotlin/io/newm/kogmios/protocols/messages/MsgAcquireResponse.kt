package io.newm.kogmios.protocols.messages

import io.newm.kogmios.protocols.model.PointDetail
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonObject

/**
 * Response that comes back from Ogmios after an acquire message is sent.
 */
@Serializable
@SerialName("Acquire")
data class MsgAcquireResponse(
    @SerialName("result")
    val result: AcquireResult,
    @SerialName("reflection")
    override val reflection: String,
) : JsonWspResponse()

@Serializable(with = AcquireSuccessOrFailureSerializer::class)
abstract class AcquireResult

@Serializable
data class AcquireSuccess(
    @SerialName("AcquireSuccess")
    val acquireSuccess: AcquireSuccessData
) : AcquireResult()

@Serializable
data class AcquireSuccessData(
    @SerialName("point")
    val point: PointDetail
)

@Serializable
data class AcquireFailure(
    @SerialName("AcquireFailure")
    val acquireFailure: AcquireFailureData
) : AcquireResult()

@Serializable
data class AcquireFailureData(
    @SerialName("failure")
    val failure: String
)

object AcquireSuccessOrFailureSerializer : JsonContentPolymorphicSerializer<AcquireResult>(AcquireResult::class) {
    override fun selectDeserializer(element: JsonElement): DeserializationStrategy<out AcquireResult> {
        return if ("AcquireSuccess" in element.jsonObject) {
            AcquireSuccess.serializer()
        } else {
            AcquireFailure.serializer()
        }
    }
}

// JSON Example
// {
//    "type": "jsonwsp/response",
//    "version": "1.0",
//    "servicename": "ogmios",
//    "methodname": "Acquire",
//    "result":
//    {
//        "AcquireFailure":
//        {
//            "failure": "pointTooOld"
//        }
//    },
//    "reflection": null
// }

// {
//    "type": "jsonwsp/response",
//    "version": "1.0",
//    "servicename": "ogmios",
//    "methodname": "Acquire",
//    "result":
//    {
//        "AcquireSuccess":
//        {
//            "point":
//            {
//                "slot": 53274613,
//                "hash": "72341672f2220429bc2944d7f83f4e45bfd250293a98ff5a14d986a9bc141b80"
//            }
//        }
//    },
//    "reflection": null
// }
