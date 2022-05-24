package io.projectnewm.kogmios.protocols.localstatequery.serializers

import io.projectnewm.kogmios.protocols.localstatequery.model.PointDetail
import io.projectnewm.kogmios.protocols.localstatequery.model.PointDetailOrOrigin
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject

object PointDetailOrOriginSerializer :
    JsonContentPolymorphicSerializer<PointDetailOrOrigin>(PointDetailOrOrigin::class) {
    override fun selectDeserializer(element: JsonElement): DeserializationStrategy<out PointDetailOrOrigin> {
        return when (element) {
            is JsonObject -> PointDetail.serializer()
            else -> OriginStringSerializer
        }
    }
}
