package io.projectnewm.kogmios.protocols.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QueryPointResult(
    @SerialName("slot")
    val slot: Long,
    @SerialName("hash")
    val hash: String,
) : QueryResult {
    fun toPointDetail(): PointDetail = PointDetail(slot = this.slot, hash = this.hash)
}
