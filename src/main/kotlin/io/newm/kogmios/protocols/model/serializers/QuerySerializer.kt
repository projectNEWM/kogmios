package io.newm.kogmios.protocols.model.serializers

import io.newm.kogmios.protocols.model.Params
import io.newm.kogmios.protocols.model.ParamsChainTip
import io.newm.kogmios.protocols.model.ParamsPoolParameters
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.jsonObject

object QuerySerializer : JsonContentPolymorphicSerializer<Params>(Params::class) {
    override fun selectDeserializer(element: JsonElement): DeserializationStrategy<Params> {
        return when ((element.jsonObject["query"] as? JsonPrimitive)?.contentOrNull) {
            "chainTip" -> ParamsChainTip.serializer()
            else -> {
                if (element.jsonObject["query"]?.jsonObject?.let { "poolParameters" in it } == true) {
                    ParamsPoolParameters.serializer()
                } else {
                    Params.serializer()
                }
            }
        }
    }
}
