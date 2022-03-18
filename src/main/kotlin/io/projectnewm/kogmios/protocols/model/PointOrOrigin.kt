package io.projectnewm.kogmios.protocols.model

import io.projectnewm.kogmios.protocols.Const.ORIGIN
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonObject

@kotlinx.serialization.Serializable(with = PointOrOriginSerializer::class)
abstract class PointOrOrigin

@kotlinx.serialization.Serializable
data class Origin(
    val point: String = ORIGIN
) : PointOrOrigin()

@kotlinx.serialization.Serializable
data class Point(
    val point: PointDetail
) : PointOrOrigin()

@kotlinx.serialization.Serializable
data class PointDetail(
    val slot: Long,
    val hash: String,
)

object PointOrOriginSerializer : JsonContentPolymorphicSerializer<PointOrOrigin>(PointOrOrigin::class) {
    override fun selectDeserializer(element: JsonElement): DeserializationStrategy<out PointOrOrigin> {
        return if (element.jsonObject["point"]?.jsonObject?.let { "slot" in it } == true) {
            Point.serializer()
        } else {
            Origin.serializer()
        }
    }
}