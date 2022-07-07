package io.projectnewm.kogmios.protocols.model.serializers

import io.projectnewm.kogmios.protocols.model.*
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonObject

object BlockSerializer : JsonContentPolymorphicSerializer<Block>(Block::class) {
    override fun selectDeserializer(element: JsonElement): DeserializationStrategy<out Block> {
        return when (val key = element.jsonObject.keys.first()) {
            "shelley" -> BlockShelley.serializer()
            "allegra" -> BlockAllegra.serializer()
            "mary" -> BlockMary.serializer()
            "alonzo" -> BlockAlonzo.serializer()
            "babbage" -> BlockBabbage.serializer()
            else -> throw IllegalStateException("Unable to find deserializer for key: $key")
        }
    }
}
