package io.newm.kogmios.protocols.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class AdaRewardsInput(
    @SerialName("ada")
    val ada: Lovelace,
) : ProjectedRewardsInput
