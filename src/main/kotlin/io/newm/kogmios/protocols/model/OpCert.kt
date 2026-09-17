package io.newm.kogmios.protocols.model

import com.fasterxml.jackson.annotation.JsonProperty

data class OpCert(
    @param:JsonProperty(value = "hotVk", required = true)
    @get:JsonProperty("hotVk")
    val hotVk: String,
    @param:JsonProperty(value = "count", required = true)
    @get:JsonProperty("count")
    val count: Int,
    @param:JsonProperty(value = "kesPeriod", required = true)
    @get:JsonProperty("kesPeriod")
    val kesPeriod: Int,
    @param:JsonProperty(value = "sigma", required = true)
    @get:JsonProperty("sigma")
    val sigma: String,
)
