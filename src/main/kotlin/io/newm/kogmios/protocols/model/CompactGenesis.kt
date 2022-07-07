package io.projectnewm.kogmios.protocols.model

import kotlinx.datetime.Instant
import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.math.BigDecimal

@Serializable
data class CompactGenesis(
    @SerialName("systemStart")
    val systemStart: Instant,
    @SerialName("networkMagic")
    val networkMagic: Long,
    @SerialName("network")
    val network: String,
    @SerialName("activeSlotsCoefficient")
    @Contextual
    val activeSlotsCoefficient: BigDecimal,
    @SerialName("securityParameter")
    val securityParameter: Long,
    @SerialName("epochLength")
    val epochLength: Long,
    @SerialName("slotsPerKesPeriod")
    val slotsPerKesPeriod: Long,
    @SerialName("maxKesEvolutions")
    val maxKesEvolutions: Long,
    @SerialName("slotLength")
    val slotLength: Long,
    @SerialName("updateQuorum")
    val updateQuorum: Long,
    @SerialName("maxLovelaceSupply")
    val maxLovelaceSupply: Long,
    @SerialName("protocolParameters")
    val protocolParameters: ProtocolParameters
) : QueryResult
