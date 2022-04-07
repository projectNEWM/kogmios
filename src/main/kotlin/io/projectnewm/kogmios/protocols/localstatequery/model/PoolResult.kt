package io.projectnewm.kogmios.protocols.localstatequery.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import java.math.BigDecimal
import java.math.BigInteger

@kotlinx.serialization.Serializable
data class PoolResult(
    @SerialName("id")
    val id: String,
    @SerialName("vrf")
    val vrf: String,
    @SerialName("pledge")
    @Contextual
    val pledge: BigInteger,
    @SerialName("cost")
    @Contextual
    val cost: BigInteger,
    @SerialName("margin")
    @Contextual
    val margin: BigDecimal,
    @SerialName("rewardAccount")
    val rewardAccount: String,
    @SerialName("owners")
    val owners: List<String>,
    @SerialName("relays")
    val relays: List<RelayResult>?,
    @SerialName("metadata")
    val metadata: MetadataResult,
)
