package io.newm.kogmios.protocols.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.math.BigInteger

@Serializable
data class PlutusCostModels(
    @SerialName("plutus:v1")
    val plutusV1: List<@Contextual BigInteger>? = null,
    @SerialName("plutus:v2")
    val plutusV2: List<@Contextual BigInteger>? = null,
    @SerialName("plutus:v3")
    val plutusV3: List<@Contextual BigInteger>? = null,
)
