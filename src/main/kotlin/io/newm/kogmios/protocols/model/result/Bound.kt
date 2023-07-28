package io.newm.kogmios.protocols.model.result

import io.newm.kogmios.protocols.model.Seconds
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Bound(
    @SerialName("time")
    val time: Seconds,
    @SerialName("slot")
    val slot: Long,
    @SerialName("epoch")
    val epoch: Long,
) : OgmiosResult
