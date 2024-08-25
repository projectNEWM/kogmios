package io.newm.kogmios.protocols.model

import io.newm.kogmios.ClientImpl
import kotlinx.serialization.Contextual
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import java.math.BigInteger

@Serializable(with = MetadataValueSerializer::class)
sealed interface MetadataValue

@Serializable(with = MetadataMapSerializer::class)
class MetadataMap :
    MetadataValue,
    MutableMap<MetadataValue, MetadataValue> by mutableMapOf() {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is MetadataMap) return false
        return true
    }

    override fun hashCode(): Int = javaClass.hashCode()

    override fun toString(): String = "MetadataMap(${this.entries.joinToString { entry -> "{${entry.key}:${entry.value}}" }})"
}

@Serializable(with = MetadataListSerializer::class)
class MetadataList :
    MetadataValue,
    MutableList<MetadataValue> by mutableListOf() {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is MetadataList) return false
        return true
    }

    override fun hashCode(): Int = javaClass.hashCode()

    override fun toString(): String = "MetadataList([${this.joinToString { metadataValue -> metadataValue.toString() }}])"
}

@Serializable
data class MetadataString(
    val string: String
) : MetadataValue

@Serializable
data class MetadataInteger(
    @Contextual val int: BigInteger
) : MetadataValue

@Serializable
data class MetadataBytes(
    val bytes: String
) : MetadataValue

object MetadataMapSerializer : KSerializer<MetadataMap> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("MetadataMap")

    override fun deserialize(decoder: Decoder): MetadataMap {
        require(decoder is JsonDecoder)
        val metadataMap = MetadataMap()
        val mapJsonElement = decoder.decodeJsonElement()
        mapJsonElement.jsonObject["map"]?.jsonArray?.forEach { mapItemJsonElement ->
            val key: MetadataValue = ClientImpl.json.decodeFromJsonElement(mapItemJsonElement.jsonObject["k"]!!)
            val value: MetadataValue = ClientImpl.json.decodeFromJsonElement(mapItemJsonElement.jsonObject["v"]!!)
            metadataMap[key] = value
        }
        return metadataMap
    }

    override fun serialize(
        encoder: Encoder,
        value: MetadataMap
    ) {
    }
}

object MetadataListSerializer : KSerializer<MetadataList> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("MetadataList")

    override fun deserialize(decoder: Decoder): MetadataList {
        require(decoder is JsonDecoder)
        val metadataList = MetadataList()
        val listJsonElement = decoder.decodeJsonElement()
        listJsonElement.jsonObject["list"]?.jsonArray?.forEach { listItemJsonElement ->
            val item: MetadataValue = ClientImpl.json.decodeFromJsonElement(listItemJsonElement)
            metadataList.add(item)
        }
        return metadataList
    }

    override fun serialize(
        encoder: Encoder,
        value: MetadataList
    ) {
    }
}

object MetadataValueSerializer : JsonContentPolymorphicSerializer<MetadataValue>(MetadataValue::class) {
    override fun selectDeserializer(element: JsonElement): DeserializationStrategy<MetadataValue> =
        if ("map" in element.jsonObject) {
            MetadataMap.serializer()
        } else if ("list" in element.jsonObject) {
            MetadataList.serializer()
        } else if ("string" in element.jsonObject) {
            MetadataString.serializer()
        } else if ("int" in element.jsonObject) {
            MetadataInteger.serializer()
        } else if ("bytes" in element.jsonObject) {
            MetadataBytes.serializer()
        } else {
            throw IllegalStateException("No serializer found!: $element")
        }
}
