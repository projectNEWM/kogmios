package io.newm.kogmios.protocols.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OpCert(
    @SerialName("hotVk")
    val hotVk: String,
    @SerialName("count")
    val count: Int,
    @SerialName("kesPeriod")
    val kesPeriod: Int,
    @SerialName("sigma")
    val sigma: String,
)
