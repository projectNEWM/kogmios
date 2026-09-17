package io.newm.kogmios.protocols.model.result

import com.fasterxml.jackson.annotation.JsonProperty

import io.newm.kogmios.protocols.model.PointDetail

/**
 * A point has been successfully acquired for querying the ledger state.
 */

data class AcquireResult(
    @param:JsonProperty(value = "acquired", required = true)
    @get:JsonProperty("acquired")
    val acquired: String,
    @param:JsonProperty(value = "point", required = true)
    @get:JsonProperty("point")
    val point: PointDetail,
) : OgmiosResult
