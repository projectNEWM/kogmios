package io.newm.kogmios.protocols.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ParamsPoolParameters(
    @SerialName("stakePools")
    val stakePools: List<StakePool>,
) : Params()
