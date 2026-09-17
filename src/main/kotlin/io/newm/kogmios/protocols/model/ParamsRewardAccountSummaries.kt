package io.newm.kogmios.protocols.model

import com.fasterxml.jackson.annotation.JsonProperty

data class ParamsRewardAccountSummaries(
    @param:JsonProperty(value = "keys", required = true)
    @get:JsonProperty("keys")
    val keys: List<String>,
) : Params()
