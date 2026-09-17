package io.newm.kogmios.protocols.model.result

import com.fasterxml.jackson.annotation.JsonProperty

import io.newm.kogmios.protocols.model.PointDetailOrOrigin
import io.newm.kogmios.protocols.model.Tip

/**
 * An intersection has been found between the requested points.
 */

data class IntersectionFoundResult(
    @param:JsonProperty(value = "intersection", required = true)
    @get:JsonProperty("intersection")
    val intersection: PointDetailOrOrigin,
    @param:JsonProperty(value = "tip", required = true)
    @get:JsonProperty("tip")
    val tip: Tip,
) : OgmiosResult
