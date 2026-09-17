package io.newm.kogmios.protocols.model

import com.fasterxml.jackson.annotation.JsonProperty

import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.annotation.JsonTypeName
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import io.newm.kogmios.serializers.DeepJsonNodeSerializer

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "language")
sealed interface Script

@JsonTypeName("native")
data class ScriptNative(
    @get:JsonSerialize(using = DeepJsonNodeSerializer::class)
    val json: JsonNode,
    val cbor: String? = null,
) : Script {
    override fun toString(): String = "ScriptNative(json=<omitted>, cbor=$cbor)"
}

@JsonTypeName("plutus:v1")
data class ScriptPlutusV1(
    @param:JsonProperty(value = "cbor", required = true)
    @get:JsonProperty("cbor")
    val cbor: String,
) : Script

@JsonTypeName("plutus:v2")
data class ScriptPlutusV2(
    @param:JsonProperty(value = "cbor", required = true)
    @get:JsonProperty("cbor")
    val cbor: String,
) : Script

@JsonTypeName("plutus:v3")
data class ScriptPlutusV3(
    @param:JsonProperty(value = "cbor", required = true)
    @get:JsonProperty("cbor")
    val cbor: String,
) : Script
