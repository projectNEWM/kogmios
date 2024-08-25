package io.newm.kogmios.protocols.model.result

import io.newm.kogmios.protocols.model.PointDetail
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TipResult(
    @SerialName("slot")
    val slot: Long,
    @SerialName("id")
    val id: String,
) : OgmiosResult {
    fun toPointDetail(): PointDetail = PointDetail(slot, id)
}
