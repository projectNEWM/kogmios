package io.newm.kogmios.protocols.model

import io.newm.kogmios.ClientImpl
import kotlinx.serialization.*
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.*
import java.math.BigDecimal

@Serializable(with = ScriptSerializer::class)
sealed class Script

// FIXME: need to handle all, any, and nested native scripts
@Serializable
data class ScriptNative(
    @SerialName("native")
    val native: ScriptNativeDetail,
) : Script()

@Serializable
data class ScriptPlutusV1(@SerialName("plutus:v1") val plutusV1: String) : Script()

@Serializable
data class ScriptPlutusV2(@SerialName("plutus:v2") val plutusV2: String) : Script()

object ScriptSerializer : JsonContentPolymorphicSerializer<Script>(Script::class) {
    override fun selectDeserializer(element: JsonElement): DeserializationStrategy<out Script> {
        return when (val key = element.jsonObject.keys.first()) {
            "native" -> ScriptNative.serializer()
            "plutus:v1" -> ScriptPlutusV1.serializer()
            "plutus:v2" -> ScriptPlutusV2.serializer()
            else -> throw IllegalStateException("Unable to find deserializer for key: $key")
        }
    }
}

@Serializable(with = ScriptNativeDetailSerializer::class)
sealed class ScriptNativeDetail

@Serializable(with = ScriptNativeDetailSignatureSerializer::class)
data class ScriptNativeDetailSignature(val signature: String) : ScriptNativeDetail()

object ScriptNativeDetailSignatureSerializer : KSerializer<ScriptNativeDetailSignature> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("ScriptNativeDetailSignature")

    override fun deserialize(decoder: Decoder): ScriptNativeDetailSignature {
        return ScriptNativeDetailSignature(decoder.decodeString())
    }

    override fun serialize(encoder: Encoder, value: ScriptNativeDetailSignature) {
        encoder.encodeString(value.signature)
    }
}

@Serializable
data class ScriptNativeDetailExpiresAt(
    @SerialName("expiresAt")
    @Contextual
    val expiresAt: BigDecimal,
) : ScriptNativeDetail()

@Serializable
data class ScriptNativeDetailStartsAt(
    @SerialName("startsAt")
    @Contextual
    val startsAt: BigDecimal,
) : ScriptNativeDetail()

@Serializable(with = ScriptNativeDetailAllSerializer::class)
class ScriptNativeDetailAll : ScriptNativeDetail(), MutableList<ScriptNativeDetail> by mutableListOf() {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ScriptNativeDetailAll) return false
        return true
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }

    override fun toString(): String {
        return "ScriptNativeDetailAll([${joinToString { it.toString() }}])"
    }
}

@Serializable(with = ScriptNativeDetailAnySerializer::class)
class ScriptNativeDetailAny : ScriptNativeDetail(), MutableList<ScriptNativeDetail> by mutableListOf() {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ScriptNativeDetailAll) return false
        return true
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }

    override fun toString(): String {
        return "ScriptNativeDetailAny([${joinToString { it.toString() }}])"
    }
}

@Serializable(with = ScriptNativeDetailNofKSerializer::class)
class ScriptNativeDetailNofK(
    val nOf: Long,
    private val delegate: MutableList<ScriptNativeDetail> = mutableListOf(),
) : ScriptNativeDetail(), MutableList<ScriptNativeDetail> by delegate {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ScriptNativeDetailNofK) return false

        if (nOf != other.nOf) return false
        if (delegate != other.delegate) return false

        return true
    }

    override fun hashCode(): Int {
        var result = nOf.hashCode()
        result = 31 * result + delegate.hashCode()
        return result
    }

    override fun toString(): String {
        return "ScriptNativeDetailNofK($nOf, [${joinToString { it.toString() }})"
    }
}

object ScriptNativeDetailAllSerializer : KSerializer<ScriptNativeDetailAll> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("ScriptNativeDetailAll")
    override fun deserialize(decoder: Decoder): ScriptNativeDetailAll {
        require(decoder is JsonDecoder)
        val scriptNativeDetailAll = ScriptNativeDetailAll()
        val listJsonElement = decoder.decodeJsonElement()
        listJsonElement.jsonObject["all"]?.jsonArray?.forEach { listItemJsonElement ->
            val item: ScriptNativeDetail = ClientImpl.json.decodeFromJsonElement(listItemJsonElement)
            scriptNativeDetailAll.add(item)
        }
        return scriptNativeDetailAll
    }

    override fun serialize(encoder: Encoder, value: ScriptNativeDetailAll) {
    }
}

object ScriptNativeDetailAnySerializer : KSerializer<ScriptNativeDetailAny> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("ScriptNativeDetailAny")
    override fun deserialize(decoder: Decoder): ScriptNativeDetailAny {
        require(decoder is JsonDecoder)
        val scriptNativeDetailAny = ScriptNativeDetailAny()
        val listJsonElement = decoder.decodeJsonElement()
        listJsonElement.jsonObject["any"]?.jsonArray?.forEach { listItemJsonElement ->
            val item: ScriptNativeDetail = ClientImpl.json.decodeFromJsonElement(listItemJsonElement)
            scriptNativeDetailAny.add(item)
        }
        return scriptNativeDetailAny
    }

    override fun serialize(encoder: Encoder, value: ScriptNativeDetailAny) {
    }
}

object ScriptNativeDetailNofKSerializer : KSerializer<ScriptNativeDetailNofK> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("ScriptNativeDetailAny")
    override fun deserialize(decoder: Decoder): ScriptNativeDetailNofK {
        require(decoder is JsonDecoder)
        val nOfKJsonElement = decoder.decodeJsonElement()
        val key = nOfKJsonElement.jsonObject.keys.first()
        val scriptNativeDetailNofK = ScriptNativeDetailNofK(key.toLong())
        nOfKJsonElement.jsonObject[key]?.jsonArray?.forEach { listItemJsonElement ->
            val item: ScriptNativeDetail = ClientImpl.json.decodeFromJsonElement(listItemJsonElement)
            scriptNativeDetailNofK.add(item)
        }
        return scriptNativeDetailNofK
    }

    override fun serialize(encoder: Encoder, value: ScriptNativeDetailNofK) {
    }
}

object ScriptNativeDetailSerializer : JsonContentPolymorphicSerializer<ScriptNativeDetail>(ScriptNativeDetail::class) {
    override fun selectDeserializer(element: JsonElement): DeserializationStrategy<out ScriptNativeDetail> {
        return if (element is JsonPrimitive) {
            ScriptNativeDetailSignature.serializer()
        } else if ("all" in element.jsonObject) {
            ScriptNativeDetailAll.serializer()
        } else if ("any" in element.jsonObject) {
            ScriptNativeDetailAny.serializer()
        } else if ("expiresAt" in element.jsonObject) {
            ScriptNativeDetailExpiresAt.serializer()
        } else if ("startsAt" in element.jsonObject) {
            ScriptNativeDetailStartsAt.serializer()
        } else if (element.jsonObject.keys.first().toLongOrNull() != null) {
            ScriptNativeDetailNofK.serializer()
        } else {
            throw IllegalArgumentException("Unable to find serializer for $element")
        }
    }
}
