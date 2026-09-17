package io.newm.kogmios.protocols.model.result

import com.fasterxml.jackson.annotation.JsonProperty

import io.newm.kogmios.protocols.model.Seconds

data class Bound(
    @param:JsonProperty(value = "time", required = true)
    @get:JsonProperty("time")
    val time: Seconds,
    @param:JsonProperty(value = "slot", required = true)
    @get:JsonProperty("slot")
    val slot: Long,
    @param:JsonProperty(value = "epoch", required = true)
    @get:JsonProperty("epoch")
    val epoch: Long,
) : OgmiosResult
