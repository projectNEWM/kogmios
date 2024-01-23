package io.newm.kogmios.protocols.model.result

import io.newm.kogmios.protocols.model.PointDetail
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * A point has been successfully acquired for querying the ledger state.
 */
@Serializable
data class AcquireResult(
    @SerialName("acquired")
    val acquired: String,
    @SerialName("point")
    val point: PointDetail,
) : OgmiosResult
