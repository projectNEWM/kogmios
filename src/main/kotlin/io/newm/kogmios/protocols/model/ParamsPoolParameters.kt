package io.newm.kogmios.protocols.model

import com.fasterxml.jackson.annotation.JsonProperty

data class ParamsPoolParameters(
    @param:JsonProperty(value = "stakePools", required = true)
    @get:JsonProperty("stakePools")
    val stakePools: List<StakePool>,
) : Params()
