package io.newm.kogmios.protocols.model.result

import io.newm.kogmios.protocols.model.CardanoEra
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HealthResult(
    @SerialName("connectionStatus")
    val connectionStatus: String,
    @SerialName("currentEpoch")
    val currentEpoch: Long,
    @SerialName("currentEra")
    val currentEra: CardanoEra,
    @SerialName("lastKnownTip")
    val lastKnownTip: LastKnownTip,
    @SerialName("lastTipUpdate")
    val lastTipUpdate: String,
    @SerialName("metrics")
    val metrics: Metrics,
    @SerialName("network")
    val network: String,
    @SerialName("networkSynchronization")
    val networkSynchronization: Double,
    @SerialName("slotInEpoch")
    val slotInEpoch: Long,
    @SerialName("startTime")
    val startTime: String,
    @SerialName("version")
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
