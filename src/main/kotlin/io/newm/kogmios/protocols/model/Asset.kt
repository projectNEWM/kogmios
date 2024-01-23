package io.newm.kogmios.protocols.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.math.BigInteger

@Serializable
data class Asset(
    @SerialName("policyId")
    val policyId: String,
    @SerialName("name")
    val name: String,
    @Contextual
    @SerialName("quantity")
    val quantity: BigInteger,
)
