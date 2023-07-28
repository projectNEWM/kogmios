package io.newm.kogmios.protocols.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ParamsGenesisConfig(
    @SerialName("era")
    val era: String,
) : Params()
