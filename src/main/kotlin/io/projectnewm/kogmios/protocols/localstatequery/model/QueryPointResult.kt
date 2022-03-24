package io.projectnewm.kogmios.protocols.localstatequery.model

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class QueryPointResult(
    @SerialName("slot")
    val slot: Long,
    @SerialName("hash")
    val hash: String,
) : QueryResult {
    fun toPointDetail(): PointDetail = PointDetail(slot = this.slot, hash = this.hash)
}