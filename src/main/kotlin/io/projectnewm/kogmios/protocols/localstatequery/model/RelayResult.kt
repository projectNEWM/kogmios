package io.projectnewm.kogmios.protocols.localstatequery.model

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class RelayResult(
    @SerialName("hostname")
    val hostname: String? = null,
    @SerialName("port")
    val port: Int? = null,
    @SerialName("ipv4")
    val ipv4: String? = null,
    @SerialName("ipv6")
    val ipv6: String? = null,
)