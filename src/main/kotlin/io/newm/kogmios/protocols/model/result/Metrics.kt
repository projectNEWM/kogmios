package io.newm.kogmios.protocols.model.result

import com.fasterxml.jackson.annotation.JsonProperty

data class Metrics(
    @param:JsonProperty(value = "activeConnections", required = true)
    @get:JsonProperty("activeConnections")
    val activeConnections: Int,
    @param:JsonProperty(value = "runtimeStats", required = true)
    @get:JsonProperty("runtimeStats")
    val runtimeStats: RuntimeStats,
    @param:JsonProperty(value = "sessionDurations", required = true)
    @get:JsonProperty("sessionDurations")
    val sessionDurations: SessionDurations,
    @param:JsonProperty(value = "totalConnections", required = true)
    @get:JsonProperty("totalConnections")
    val totalConnections: Long,
    @param:JsonProperty(value = "totalMessages", required = true)
    @get:JsonProperty("totalMessages")
    val totalMessages: Long,
    @param:JsonProperty(value = "totalUnrouted", required = true)
    @get:JsonProperty("totalUnrouted")
    val totalUnrouted: Long,
)
