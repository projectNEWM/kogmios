package io.newm.kogmios.protocols.model.result

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RuntimeStats(
    @SerialName("cpuTime")
    val cpuTime: Long,
    @SerialName("currentHeapSize")
    val currentHeapSize: Long,
    @SerialName("gcCpuTime")
    val gcCpuTime: Long,
    @SerialName("maxHeapSize")
    val maxHeapSize: Long,
)
