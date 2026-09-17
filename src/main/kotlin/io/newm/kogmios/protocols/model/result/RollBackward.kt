package io.newm.kogmios.protocols.model.result

import com.fasterxml.jackson.annotation.JsonProperty

import com.fasterxml.jackson.annotation.JsonTypeName

import io.newm.kogmios.protocols.model.PointDetailOrOrigin
import io.newm.kogmios.protocols.model.Tip

/**
 * The blockchain has been rolled back to the specified point.
 */

@JsonTypeName("backward")
data class RollBackward(
    @param:JsonProperty(value = "point", required = true)
    @get:JsonProperty("point")
    val point: PointDetailOrOrigin,
    @param:JsonProperty(value = "tip", required = true)
    @get:JsonProperty("tip")
    val tip: Tip
) : NextBlockResult
