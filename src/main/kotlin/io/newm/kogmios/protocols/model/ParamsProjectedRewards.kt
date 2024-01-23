package io.newm.kogmios.protocols.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ParamsProjectedRewards(
    @SerialName("stake")
    val stake: List<AdaRewardsInput>,
    @SerialName("scripts")
    val scripts: List<String>,
    @SerialName("keys")
    val keys: List<String>,
) : Params()
