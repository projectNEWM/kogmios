package io.newm.kogmios.protocols.model.result

import io.newm.kogmios.protocols.model.PointDetailOrOrigin
import io.newm.kogmios.protocols.model.Tip
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * The blockchain has been rolled back to the specified point.
 */
@Serializable
@SerialName("backward")
data class RollBackward(
    @SerialName("point")
    val point: PointDetailOrOrigin,
    @SerialName("tip")
    val tip: Tip
) : NextBlockResult
