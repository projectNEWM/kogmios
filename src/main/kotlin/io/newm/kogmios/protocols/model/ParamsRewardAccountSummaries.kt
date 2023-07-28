package io.newm.kogmios.protocols.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ParamsRewardAccountSummaries(
    @SerialName("keys")
    val keys: List<String>,
) : Params()
