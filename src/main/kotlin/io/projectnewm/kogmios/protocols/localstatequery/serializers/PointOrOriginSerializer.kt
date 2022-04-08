package io.projectnewm.kogmios.protocols.localstatequery.serializers

import io.projectnewm.kogmios.protocols.localstatequery.model.Origin
import io.projectnewm.kogmios.protocols.localstatequery.model.Point
import io.projectnewm.kogmios.protocols.localstatequery.model.PointOrOrigin
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
