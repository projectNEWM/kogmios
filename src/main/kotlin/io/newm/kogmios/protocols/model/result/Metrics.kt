package io.newm.kogmios.protocols.model.result

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Metrics(
    @SerialName("activeConnections")
    val activeConnections: Int,
    @SerialName("runtimeStats")
    val runtimeStats: RuntimeStats,
    @SerialName("sessionDurations")
    val sessionDurations: SessionDurations,
    @SerialName("totalConnections")
    val totalConnections: Long,
    @SerialName("totalMessages")
    val totalMessages: Long,
    @SerialName("totalUnrouted")
    val totalUnrouted: Long,
)
