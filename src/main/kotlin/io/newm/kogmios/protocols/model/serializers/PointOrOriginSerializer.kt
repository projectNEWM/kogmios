package io.newm.kogmios.protocols.model.serializers

import io.newm.kogmios.protocols.model.Origin
import io.newm.kogmios.protocols.model.Point
import io.newm.kogmios.protocols.model.PointOrOrigin
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonObject

object PointOrOriginSerializer : JsonContentPolymorphicSerializer<PointOrOrigin>(PointOrOrigin::class) {
    override fun selectDeserializer(element: JsonElement): DeserializationStrategy<out PointOrOrigin> {
        return if (element.jsonObject["point"]?.jsonObject?.let { "slot" in it } == true) {
            Point.serializer()
        } else {
            Origin.serializer()
        }
    }
}
