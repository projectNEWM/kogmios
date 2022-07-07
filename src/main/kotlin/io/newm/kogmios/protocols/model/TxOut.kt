package io.newm.kogmios.protocols.model

import io.newm.kogmios.protocols.model.serializers.AssetSerializer
import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.math.BigInteger

@Serializable
data class TxOut(
    @SerialName("address")
    val address: String,
    @SerialName("value")
    val value: Value,
    @SerialName("datum")
    val datum: String?,
)

@Serializable
data class Value(
    @SerialName("coins")
    @Contextual
    val coins: BigInteger,
    @SerialName("assets")
    @Serializable(with = AssetSerializer::class)
    val assets: List<Asset>
)

@Serializable
data class Asset(
    val policyId: String,
    val name: String,
    @Contextual
    val quantity: BigInteger,
)
