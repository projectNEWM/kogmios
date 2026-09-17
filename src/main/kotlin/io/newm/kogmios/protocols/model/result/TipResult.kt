package io.newm.kogmios.protocols.model.result

import com.fasterxml.jackson.annotation.JsonProperty

import io.newm.kogmios.protocols.model.PointDetail

data class TipResult(
    @param:JsonProperty(value = "slot", required = true)
    @get:JsonProperty("slot")
    val slot: Long,
    @param:JsonProperty(value = "id", required = true)
    @get:JsonProperty("id")
    val id: String,
) : OgmiosResult {
    fun toPointDetail(): PointDetail = PointDetail(slot, id)
}
