package io.newm.kogmios.protocols.model

import com.fasterxml.jackson.annotation.JsonProperty

import java.math.BigInteger

sealed interface MetadataValue

class MetadataMap :
    MetadataValue,
    MutableMap<MetadataValue, MetadataValue> by mutableMapOf() {
    override fun equals(other: Any?): Boolean = this === other || other is MetadataMap

    override fun hashCode(): Int = javaClass.hashCode()

    override fun toString(): String = "MetadataMap(${entries.joinToString { "{${it.key}:${it.value}}" }})"
}

class MetadataList :
    MetadataValue,
    MutableList<MetadataValue> by mutableListOf() {
    override fun equals(other: Any?): Boolean = this === other || other is MetadataList

    override fun hashCode(): Int = javaClass.hashCode()

    override fun toString(): String = "MetadataList([${joinToString()}])"
}

data class MetadataString(
    @param:JsonProperty(value = "string", required = true)
    @get:JsonProperty("string")
    val string: String,
) : MetadataValue

data class MetadataInteger(
    @param:JsonProperty(value = "int", required = true)
    @get:JsonProperty("int")
    val int: BigInteger,
) : MetadataValue

data class MetadataBytes(
    @param:JsonProperty(value = "bytes", required = true)
    @get:JsonProperty("bytes")
    val bytes: String,
) : MetadataValue
