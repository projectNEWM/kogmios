package io.newm.kogmios.protocols.model.result

import com.fasterxml.jackson.annotation.JsonProperty

import io.newm.kogmios.protocols.model.CardanoEra

data class HealthResult(
    @param:JsonProperty(value = "connectionStatus", required = true)
    @get:JsonProperty("connectionStatus")
    val connectionStatus: String,
    @param:JsonProperty(value = "currentEpoch", required = true)
    @get:JsonProperty("currentEpoch")
    val currentEpoch: Long,
    @param:JsonProperty(value = "currentEra", required = true)
    @get:JsonProperty("currentEra")
    val currentEra: CardanoEra,
    @param:JsonProperty(value = "lastKnownTip", required = true)
    @get:JsonProperty("lastKnownTip")
    val lastKnownTip: LastKnownTip,
    @param:JsonProperty(value = "lastTipUpdate", required = true)
    @get:JsonProperty("lastTipUpdate")
    val lastTipUpdate: String,
    @param:JsonProperty(value = "metrics", required = true)
    @get:JsonProperty("metrics")
    val metrics: Metrics,
    @param:JsonProperty(value = "network", required = true)
    @get:JsonProperty("network")
    val network: String,
    @param:JsonProperty(value = "networkSynchronization", required = true)
    @get:JsonProperty("networkSynchronization")
    val networkSynchronization: Double,
    @param:JsonProperty(value = "slotInEpoch", required = true)
    @get:JsonProperty("slotInEpoch")
    val slotInEpoch: Long,
    @param:JsonProperty(value = "startTime", required = true)
    @get:JsonProperty("startTime")
    val startTime: String,
    @param:JsonProperty(value = "version", required = true)
    @get:JsonProperty("version")
    val version: String
)

// {
//    "metrics": {
//        "totalUnrouted": 1,
//        "totalMessages": 30029,
//        "runtimeStats": {
//            "gcCpuTime": 1233009354,
//            "cpuTime": 81064672549,
//            "maxHeapSize": 41630,
//            "currentHeapSize": 1014
//        },
//        "totalConnections": 10,
//        "sessionDurations": {
//            "max": 57385,
//            "mean": 7057,
//            "min": 0
//        },
//        "activeConnections": 0
//    },
//    "startTime": "2021-03-15T16:16:41.470782977Z",
//    "lastTipUpdate": "2021-03-15T16:28:36.853115034Z",
//    "lastKnownTip": {
//        "hash": "c29428f386c701c1d1ba1fd259d4be78921ee9ee6c174eac898245ceb55e8061",
//        "blockNo": 5034297,
//        "slot": 15520688
//    },
//    "networkSynchronization": 0.99,
//    "currentEra": "mary",
//    "connectionStatus": "disconnected",
//    "currentEpoch": 164,
//    "slotInEpoch": 324543,
//    "version": "6.0.0",
//    "network": "mainnet"
// }
