package io.newm.kogmios.protocols.model.result

import io.newm.kogmios.protocols.model.PointDetailOrOrigin
import io.newm.kogmios.protocols.model.Tip
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * An intersection has been found between the requested points.
 */
@Serializable
data class IntersectionFoundResult(
    @SerialName("intersection")
    val intersection: PointDetailOrOrigin,
    @SerialName("tip")
    val tip: Tip,
) : OgmiosResult
