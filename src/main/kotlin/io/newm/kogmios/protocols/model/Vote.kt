package io.newm.kogmios.protocols.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class Vote {
    @SerialName("yes")
    YES,

    @SerialName("no")
    NO,

    @SerialName("abstain")
    ABSTAIN,
}
