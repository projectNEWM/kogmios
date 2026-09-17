package io.newm.kogmios.protocols.model

import com.fasterxml.jackson.annotation.JsonProperty

import com.fasterxml.jackson.annotation.JsonTypeInfo

import com.fasterxml.jackson.annotation.JsonTypeName

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
sealed interface RelayResult

@JsonTypeName("hostname")
data class HostnameRelayResult(
    @param:JsonProperty(value = "hostname", required = true)
    @get:JsonProperty("hostname")
    val hostname: String,
    val port: Int? = null,
) : RelayResult

@JsonTypeName("ipAddress")
data class IpAddressRelayResult(
    val ipv4: String? = null,
    val ipv6: String? = null,
    @param:JsonProperty(value = "port", required = true)
    @get:JsonProperty("port")
    val port: Int,
) : RelayResult
