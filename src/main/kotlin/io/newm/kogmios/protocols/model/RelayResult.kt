package io.newm.kogmios.protocols.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonClassDiscriminator

@Serializable
@JsonClassDiscriminator("type")
sealed interface RelayResult

@Serializable
@SerialName("hostname")
data class HostnameRelayResult(
    @SerialName("hostname")
    val hostname: String,
    @SerialName("port")
    val port: Int? = null,
) : RelayResult

@Serializable
@SerialName("ipAddress")
data class IpAddressRelayResult(
    @SerialName("ipv4")
    val ipv4: String? = null,
    @SerialName("ipv6")
    val ipv6: String? = null,
    @SerialName("port")
    val port: Int,
) : RelayResult
