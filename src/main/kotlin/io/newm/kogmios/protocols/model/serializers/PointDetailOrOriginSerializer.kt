package io.newm.kogmios.protocols.model.serializers

import io.newm.kogmios.protocols.model.PointDetail
import io.newm.kogmios.protocols.model.PointDetailOrOrigin
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject

object PointDetailOrOriginSerializer :
    JsonContentPolymorphicSerializer<PointDetailOrOrigin>(PointDetailOrOrigin::class) {
    override fun selectDeserializer(element: JsonElement): DeserializationStrategy<PointDetailOrOrigin> =
        when (element) {
            is JsonObject -> PointDetail.serializer()
            else -> OriginStringSerializer
        }
}
