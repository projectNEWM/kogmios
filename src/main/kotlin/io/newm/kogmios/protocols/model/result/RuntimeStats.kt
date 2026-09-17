package io.newm.kogmios.protocols.model.result

import com.fasterxml.jackson.annotation.JsonProperty

data class RuntimeStats(
    @param:JsonProperty(value = "cpuTime", required = true)
    @get:JsonProperty("cpuTime")
    val cpuTime: Long,
    @param:JsonProperty(value = "currentHeapSize", required = true)
    @get:JsonProperty("currentHeapSize")
    val currentHeapSize: Long,
    @param:JsonProperty(value = "gcCpuTime", required = true)
    @get:JsonProperty("gcCpuTime")
    val gcCpuTime: Long,
    @param:JsonProperty(value = "maxHeapSize", required = true)
    @get:JsonProperty("maxHeapSize")
    val maxHeapSize: Long,
)
