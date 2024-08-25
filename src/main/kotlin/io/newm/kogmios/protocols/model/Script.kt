package io.newm.kogmios.protocols.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonClassDiscriminator
import java.math.BigInteger

@Serializable
@JsonClassDiscriminator("language")
sealed interface Script

@Serializable
@SerialName("native")
data class ScriptNative(
    @SerialName("json")
    val json: ScriptNativeDetail,
    @SerialName("cbor")
    val cbor: String? = null,
) : Script

@Serializable
@SerialName("plutus:v1")
data class ScriptPlutusV1(
    @SerialName("cbor") val cbor: String
) : Script

@Serializable
@SerialName("plutus:v2")
data class ScriptPlutusV2(
    @SerialName("cbor") val cbor: String
) : Script

@Serializable
@SerialName("plutus:v3")
data class ScriptPlutusV3(
    @SerialName("cbor") val cbor: String
) : Script

@Serializable
@JsonClassDiscriminator("clause")
sealed interface ScriptNativeDetail

@Serializable
@SerialName("signature")
data class ScriptNativeDetailSignature(
    @SerialName("from")
    val from: String
) : ScriptNativeDetail

@Serializable
@SerialName("before")
data class ScriptNativeDetailBefore(
    @Contextual
    @SerialName("slot")
    val slot: BigInteger,
) : ScriptNativeDetail

@Serializable
@SerialName("after")
data class ScriptNativeDetailAfter(
    @Contextual
    @SerialName("slot")
    val after: BigInteger,
) : ScriptNativeDetail

@Serializable
@SerialName("all")
data class ScriptNativeDetailAll(
    @SerialName("from")
    val from: List<ScriptNativeDetail>
) : ScriptNativeDetail

@Serializable
@SerialName("any")
data class ScriptNativeDetailAny(
    @SerialName("from")
    val from: List<ScriptNativeDetail>
) : ScriptNativeDetail

@Serializable
@SerialName("some")
data class ScriptNativeDetailSome(
    @SerialName("atLeast")
    val atLeast: Long,
    @SerialName("from")
    val from: List<ScriptNativeDetail>
) : ScriptNativeDetail
