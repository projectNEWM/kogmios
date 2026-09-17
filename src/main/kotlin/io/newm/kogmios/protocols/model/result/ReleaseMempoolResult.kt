package io.newm.kogmios.protocols.model.result

import com.fasterxml.jackson.annotation.JsonProperty

data class ReleaseMempoolResult(
    @param:JsonProperty(value = "released", required = true)
    @get:JsonProperty("released")
    val released: String,
) : OgmiosResult
